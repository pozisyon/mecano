package com.annuaire.api.controller;

import com.annuaire.core.model.Member;
import com.annuaire.core.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin("*")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping
    public List<Member> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Member add(@RequestBody Member member) {
        return service.save(member);
    }
}

