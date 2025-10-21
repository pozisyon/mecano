package com.annuaire.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "members")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false) private String nom;
    @Column(nullable=false) private String prenom;
    @Column(nullable=false) private String categorie; // PROF|AUX|ETUDIANT
    @Column(unique=true) private String matricule; // si étudiant
    @Column(nullable=false, unique=true) private String email;
    private String telephone; // si PROF/AUX
    private String domaine;
    @Column(nullable=false) private boolean red = false;
}
