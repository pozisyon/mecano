package com.annuaire.core.repository;

import com.annuaire.core.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByCategorieIgnoreCase(String categorie);
    List<Member> findByDomaineContainingIgnoreCase(String domaine);
    List<Member> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrEmailContainingIgnoreCaseOrMatriculeContainingIgnoreCase(
        String nom, String prenom, String email, String matricule);
}
