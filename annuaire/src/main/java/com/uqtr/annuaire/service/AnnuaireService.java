package com.uqtr.annuaire.service;

import com.uqtr.annuaire.dao.DatabaseManager;
import com.uqtr.annuaire.model.Membre;

import java.util.List;

public class AnnuaireService {
    private final DatabaseManager db;

    public AnnuaireService(DatabaseManager db) {
        this.db = db;
    }

    public List<Membre> listByCategory(String category) {
        return db.getMembersByCategory(category);
    }

    public List<Membre> search(String query) {
        return db.searchMembers(query);
    }

    public int addMember(Membre m) {
        return db.insertMembre(m);
    }

    public boolean updateMember(Membre m) {
        return db.updateMembre(m);
    }

    public boolean deleteMember(int id) {
        return db.deleteMembre(id);
    }

    public boolean setListeRouge(int id, boolean val) {
        return db.setListeRouge(id, val);
    }
}
