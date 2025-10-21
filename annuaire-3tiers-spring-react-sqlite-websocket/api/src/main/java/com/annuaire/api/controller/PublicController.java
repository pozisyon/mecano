package com.annuaire.api.controller;

import com.annuaire.core.dto.MemberDto;
import com.annuaire.core.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/public")
@CrossOrigin("*")
@RequiredArgsConstructor
public class PublicController {
    private final MemberService service;

    @GetMapping("/category/{cat}")
    public List<MemberDto> listByCategory(@PathVariable String cat){
        return service.listByCategoriePublic(cat);
    }

    @GetMapping("/profs/domain/{dom}")
    public List<MemberDto> listProfByDomain(@PathVariable String dom){
        return service.listProfByDomainePublic(dom);
    }

    @GetMapping("/search")
    public List<MemberDto> search(@RequestParam String q){
        return service.searchPublic(q);
    }
}
