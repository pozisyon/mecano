package com.demo.demo.mapper;

import com.demo.demo.dto.PersonneDTO;
import com.demo.demo.model.Personne;
import com.demo.demo.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PersonneMapper {
    @Autowired
    PersonneService personneService;

    public Optional<Personne> fromDTO(PersonneDTO personne){
        return personneService.getPersonne(personne.getId());
    }
    public PersonneDTO toDTO(Personne personne){
        PersonneDTO personneDTO = new PersonneDTO();
        personneDTO.setEmail(personne.getEmail());
        personneDTO.setNom(personneDTO.getNom());
        personneDTO.setPrenom(personneDTO.getPrenom());
        return personneDTO;
    }
}
