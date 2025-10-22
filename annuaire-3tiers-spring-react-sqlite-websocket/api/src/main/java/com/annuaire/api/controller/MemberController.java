package com.annuaire.api.controller;

import com.annuaire.core.model.Member;
import com.annuaire.core.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin("*")
public class MemberController {

    private final MemberService service;
    @Autowired
    private SimpMessagingTemplate template;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping
    public List<Member> getAll() {

        return service.findAll();
    }

    @PostMapping
    public Member add(@RequestBody Member member) {
        Member m = service.save(member);
        broadcastMembers();
        return m;
    }
    @GetMapping("/search")
    public List<Member> search(
            @RequestParam(required=false) String categorie,
            @RequestParam(required=false) String domaine,
            @RequestParam(required=false) String query
    ) {
        // On peut faire un filtre combiné
        return service.findAll().stream()
                .filter(m -> categorie == null || m.getCategorie().equalsIgnoreCase(categorie))
                .filter(m -> domaine == null || (m.getDomaine() != null && m.getDomaine().toLowerCase().contains(domaine.toLowerCase())))
                .filter(m -> query == null || (
                        m.getNom().toLowerCase().contains(query.toLowerCase()) ||
                                m.getPrenom().toLowerCase().contains(query.toLowerCase()) ||
                                m.getEmail().toLowerCase().contains(query.toLowerCase()) ||
                                (m.getMatricule() != null && m.getMatricule().toLowerCase().contains(query.toLowerCase()))
                ))
                .map(m -> {
                    if (m.isRed()) {
                        // masque infos sensibles
                        Member light = new Member();
                        light.setNom(m.getNom());
                        light.setPrenom(m.getPrenom());
                        light.setRed(true);
                        return light;
                    }
                    return m;
                })
                .toList();
    }



    private void broadcastMembers() {
        template.convertAndSend("/topic/members", "refresh");
    }

}

