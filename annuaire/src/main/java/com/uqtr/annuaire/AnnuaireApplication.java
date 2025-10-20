package com.uqtr.annuaire;

import com.uqtr.annuaire.dao.DatabaseManager;
import com.uqtr.annuaire.model.Membre;
import com.uqtr.annuaire.service.AnnuaireService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.List;

@SpringBootApplication
public class AnnuaireApplication {

	public static void main(String[] args) {
		// === Vérification que le dossier data existe ===
		File dataDir = new File("data");
		if (!dataDir.exists()) {
			boolean created = dataDir.mkdirs();
			if (created)
				System.out.println(" Dossier 'data' créé automatiquement.");
			else
				System.err.println(" Impossible de créer le dossier 'data' !");
		}

		// === Initialisation de la base et du service ===
		DatabaseManager db = new DatabaseManager("data/annuaire.db");
		AnnuaireService service = new AnnuaireService(db);

//		// === Test : ajouter un membre ===
//		Membre m = new Membre();
//		m.setNom("Doe");
//		m.setPrenom("John");
//		m.setCategorie("etudiant");
//		m.setMatricule("12345");
//		m.setEmail("john.doe@uqtr.ca");
//		m.setTelephone("819-555-1234");
//		m.setDomaine("Réseaux");
//		m.setListeRouge(false);
//
//		int id = service.addMember(m);
//		System.out.println(" Membre ajouté avec ID : " + id);

		// === Test : afficher tous les étudiants ===
		List<Membre> etudiants = service.listByCategory("etudiant");
		System.out.println("📋 Liste des étudiants (" + etudiants.size() + ") :");
		for (Membre e : etudiants) {
			System.out.println(" - " + e.getPrenom() + " " + e.getNom() + " (" + e.getCategorie() + ")");
		}

		// === Lancement du serveur Spring Boot ===
		SpringApplication.run(AnnuaireApplication.class, args);
	}

	@Bean
	public DatabaseManager databaseManager() {
		return new DatabaseManager("data/annuaire.db");
	}

	@Bean
	public AnnuaireService annuaireService(DatabaseManager db) {
		return new AnnuaireService(db);
	}
}