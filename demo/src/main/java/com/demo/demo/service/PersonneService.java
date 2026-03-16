package com.demo.demo.service;

import com.demo.demo.model.Personne;
import com.demo.demo.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PersonneService {
    @Autowired
    private PersonneRepository personneRepository;

    public Optional<Personne> getPersonne(Long id){
        return personneRepository.findById(id);
    }

    public List<Personne> getPersonnes(){
        return personneRepository.findAll();
    }

    public Personne save(Personne personne){
         return personneRepository.save(personne);
    }

    public void deleteById(Long id){
        personneRepository.deleteById(id);

    }
}
