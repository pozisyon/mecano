package com.annuaire.core.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MemberDto {
    private Long id;
    private String nom;
    private String prenom;
    private String categorie;
    private String matricule;
    private String email;
    private String telephone;
    private String domaine;
    private boolean red;
}
