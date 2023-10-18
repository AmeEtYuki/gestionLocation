package com.example.test3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Meuble {
    int id;
    int id_typeEquipement;
    int id_pieces;
    String type, libelle;

    public Meuble(int id, String type, String libelle, int id_typeEquipement, int id_pieces ) {
        this.id = id;
        this.type = type;
        this.libelle = libelle;
        this.id_typeEquipement = id_typeEquipement;
        this.id_pieces = id_pieces;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public void delMeuble() {
        DatabaseAccess b = new DatabaseAccess();
        try {
            Connection co = b.getConnection();
            PreparedStatement ps = co.prepareStatement("DELETE FROM 'equipements' WHERE 'id' = ?");
            ps.setInt(1, this.id);
            ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void addMeuble() {
        DatabaseAccess bdd = new DatabaseAccess();
        try {
            Connection co = bdd.getConnection();
            PreparedStatement ps = co.prepareStatement("INSERT INTO equipements (libelle, id_typeEquipement, id_pieces) VALUES (?, ?, ?)");
            ps.setString(1, this.libelle);
            ps.setInt(2, this.id_typeEquipement);
            ps.setInt(3, this.id_pieces);
            ResultSet rs = ps.executeQuery();
            System.out.println("L'objet a bien était ajouté !");

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}

