package com.annuaire.api.controller;

import com.annuaire.core.dto.MemberDto;
import com.annuaire.core.model.Member;
import com.annuaire.core.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AdminController {
    private final MemberService service;

    @GetMapping("/members")
    public List<MemberDto> listAll(){ return service.listAllAdmin(); }

    @PostMapping("/members")
    public Member add(@RequestBody Member m){ return service.add(m); }

    @PutMapping("/members")
    public Member update(@RequestBody Member m){ return service.update(m); }

    @DeleteMapping("/members/{id}")
    public void delete(@PathVariable Long id){ service.delete(id); }

    @PutMapping("/members/{id}/red/{red}")
    public Member setRed(@PathVariable Long id, @PathVariable boolean red){ return service.setRed(id, red); }
}
