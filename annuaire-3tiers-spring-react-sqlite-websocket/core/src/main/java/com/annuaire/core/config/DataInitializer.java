package com.annuaire.core.config;

import com.annuaire.core.model.Member;
import com.annuaire.core.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        System.out.println("➡️ Initialisation des données de test...");
        memberRepository.deleteAll(); // 🔥 force nettoyage pour tester

        memberRepository.save(Member.builder()
                .nom("Tremblay").prenom("Jean")
                .categorie("PROF")
                .email("jean.tremblay@uqtr.ca")
                .telephone("819-555-0101")
                .domaine("Réseaux")
                .red(false)
                .build());

        System.out.println("✅ Données de test insérées !");
    }

}
