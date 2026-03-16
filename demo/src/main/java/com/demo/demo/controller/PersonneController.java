package com.demo.demo.controller;

import com.demo.demo.model.Personne;
import com.demo.demo.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/entities")
public class PersonneController {

    private final PersonneService personneService;

    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }

    @GetMapping
    public List<Personne> getAll() {
        return personneService.getPersonnes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personne> getById(@PathVariable long id) {
        return personneService.getPersonne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Personne> create(@RequestBody Personne personne) {
        Personne saved = personneService.save(personne);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personne> update(@PathVariable Long id, @RequestBody Personne personne) {
        return personneService.getPersonne(id)
                .map(existing -> {
                    personne.setId(id);
                    return ResponseEntity.ok(personneService.save(personne));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personneService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

