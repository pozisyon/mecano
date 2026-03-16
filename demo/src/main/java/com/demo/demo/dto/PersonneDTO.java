package com.demo.demo.dto;

import com.demo.demo.model.Personne;

public class PersonneDTO {
    private long id;
    private String nom;
    private String prenom;
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return this.getPrenom()+" "+this.getNom()+" "+this.getEmail();
    }
}
