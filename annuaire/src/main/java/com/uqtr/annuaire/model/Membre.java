package com.uqtr.annuaire.model;

public class Membre {
    private int id;
    private String nom;
    private String prenom;
    private String categorie;
    private String matricule;
    private String email;
    private String telephone;
    private String domaine;
    private boolean listeRouge;

    public Membre() {}

    // constructeur utile
    public Membre(int id, String nom, String prenom, String categorie,
                  String matricule, String email, String telephone, String domaine, boolean listeRouge) {
        this.id = id; this.nom = nom; this.prenom = prenom; this.categorie = categorie;
        this.matricule = matricule; this.email = email; this.telephone = telephone;
        this.domaine = domaine; this.listeRouge = listeRouge;
    }

    // getters / setters (idéales pour Gson)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getDomaine() { return domaine; }
    public void setDomaine(String domaine) { this.domaine = domaine; }
    public boolean isListeRouge() { return listeRouge; }
    public void setListeRouge(boolean listeRouge) { this.listeRouge = listeRouge; }
    @Override
    public String toString() {
        return "Membre{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", categorie='" + categorie + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}