package com.annuaire.core.service;

import com.annuaire.core.dto.MemberDto;
import com.annuaire.core.mapper.MemberMapper;
import com.annuaire.core.model.Member;
import com.annuaire.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository repo;

    public List<Member> findAll() {
        return repo.findAll();
    }

    public Member save(Member m) {
        return repo.save(m);
    }

    // Public (1-3)
    @Transactional(readOnly = true)
    public List<MemberDto> listByCategoriePublic(String cat){
        return repo.findByCategorieIgnoreCase(cat).stream()
            .map(MemberMapper::toPublicDto).toList();
    }
    @Transactional(readOnly = true)
    public List<MemberDto> listProfByDomainePublic(String dom){
        return repo.findByDomaineContainingIgnoreCase(dom).stream()
            .filter(m -> "PROF".equalsIgnoreCase(m.getCategorie()))
            .map(MemberMapper::toPublicDto).toList();
    }
    @Transactional(readOnly = true)
    public List<MemberDto> searchPublic(String q){
        return repo.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrEmailContainingIgnoreCaseOrMatriculeContainingIgnoreCase(q,q,q,q)
            .stream().map(MemberMapper::toPublicDto).toList();
    }

    // Admin (4-8)
    @Transactional public Member add(Member m){ return repo.save(m); }
    @Transactional public Member update(Member m){ return repo.save(m); }
    @Transactional public void delete(Long id){ repo.deleteById(id); }
    @Transactional public Member setRed(Long id, boolean red){
        Member m = repo.findById(id).orElseThrow();
        m.setRed(red);
        return repo.save(m);
    }
    @Transactional(readOnly = true) public List<MemberDto> listAllAdmin(){
        return repo.findAll().stream().map(MemberMapper::toAdminDto).toList();
    }
}
