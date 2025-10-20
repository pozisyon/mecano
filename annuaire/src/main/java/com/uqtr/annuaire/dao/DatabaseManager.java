package com.uqtr.annuaire.dao;

import com.uqtr.annuaire.model.Membre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private final String url;

    public DatabaseManager(String dbPath) {
        this.url = "jdbc:sqlite:" + dbPath;
        initDatabase();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    /** Crée la table si elle n’existe pas **/
    private void initDatabase() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Membre (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nom TEXT NOT NULL,
                prenom TEXT NOT NULL,
                categorie TEXT NOT NULL,
                matricule TEXT,
                email TEXT,
                telephone TEXT,
                domaine TEXT,
                liste_rouge INTEGER DEFAULT 0
            );
        """;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Ajouter un membre **/
    public int insertMembre(Membre m) {
        String sql = "INSERT INTO Membre (nom, prenom, categorie, matricule, email, telephone, domaine, liste_rouge) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, m.getNom());
            ps.setString(2, m.getPrenom());
            ps.setString(3, m.getCategorie());
            ps.setString(4, m.getMatricule());
            ps.setString(5, m.getEmail());
            ps.setString(6, m.getTelephone());
            ps.setString(7, m.getDomaine());
            ps.setInt(8, m.isListeRouge() ? 1 : 0);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /** Liste des membres par catégorie **/
    public List<Membre> getMembersByCategory(String category) {
        List<Membre> list = new ArrayList<>();
        String sql = "SELECT * FROM Membre WHERE categorie = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /** Rechercher un membre **/
    public List<Membre> searchMembers(String query) {
        List<Membre> list = new ArrayList<>();
        String sql = "SELECT * FROM Membre WHERE nom LIKE ? OR prenom LIKE ? OR matricule LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String q = "%" + query + "%";
            ps.setString(1, q);
            ps.setString(2, q);
            ps.setString(3, q);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /** Suppression **/
    public boolean deleteMembre(int id) {
        String sql = "DELETE FROM Membre WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** Mise à jour **/
    public boolean updateMembre(Membre m) {
        String sql = """
            UPDATE Membre SET nom=?, prenom=?, categorie=?, matricule=?, email=?, telephone=?, domaine=?, liste_rouge=?
            WHERE id=?;
        """;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getNom());
            ps.setString(2, m.getPrenom());
            ps.setString(3, m.getCategorie());
            ps.setString(4, m.getMatricule());
            ps.setString(5, m.getEmail());
            ps.setString(6, m.getTelephone());
            ps.setString(7, m.getDomaine());
            ps.setInt(8, m.isListeRouge() ? 1 : 0);
            ps.setInt(9, m.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** Marquer liste rouge **/
    public boolean setListeRouge(int id, boolean val) {
        String sql = "UPDATE Membre SET liste_rouge = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, val ? 1 : 0);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Membre mapRow(ResultSet rs) throws SQLException {
        return new Membre(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("categorie"),
                rs.getString("matricule"),
                rs.getString("email"),
                rs.getString("telephone"),
                rs.getString("domaine"),
                rs.getInt("liste_rouge") == 1
        );
    }
}
