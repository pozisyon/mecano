package com.annuaire.core.mapper;

import com.annuaire.core.dto.MemberDto;
import com.annuaire.core.model.Member;

public class MemberMapper {
    public static MemberDto toPublicDto(Member m) {
        if (!m.isRed()) {
            return MemberDto.builder()
                .id(m.getId()).nom(m.getNom()).prenom(m.getPrenom())
                .categorie(m.getCategorie()).matricule(m.getMatricule())
                .email(m.getEmail()).telephone(m.getTelephone())
                .domaine(m.getDomaine()).red(false).build();
        }
        return MemberDto.builder()
            .id(m.getId()).nom(m.getNom()).prenom(m.getPrenom())
            .categorie(m.getCategorie()).red(true).build();
    }
    public static MemberDto toAdminDto(Member m) {
        return MemberDto.builder()
            .id(m.getId()).nom(m.getNom()).prenom(m.getPrenom())
            .categorie(m.getCategorie()).matricule(m.getMatricule())
            .email(m.getEmail()).telephone(m.getTelephone())
            .domaine(m.getDomaine()).red(m.isRed()).build();
    }
}
