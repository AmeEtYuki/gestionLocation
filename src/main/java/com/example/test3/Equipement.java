package com.example.test3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Equipement {
    int id;
    int id_pieces;
    String libelle;

    public Equipement(int id, String libelle, int id_pieces) {
        this.id = id;
        this.libelle = libelle;
        this.id_pieces = id_pieces;
    }

    public int getId_pieces() {
        return id_pieces;
    }

    public int getId() {
        return id;
    }


    public String getLibelle() {
        return libelle;
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
            co.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /*public void addMeuble() {
        DatabaseAccess bdd = new DatabaseAccess();
        try {
            Connection co = bdd.getConnection();
            PreparedStatement ps = co.prepareStatement("INSERT INTO equipements (libelle, id_pieces) VALUES (?, ?)");
            ps.setString(1, this.libelle);
            ps.setInt(2, this.id_pieces);
            int rs = ps.executeUpdate();
            ResultSet id = ps.getGeneratedKeys();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }*/
    //Modification d'un meuble abandonnées
    public void modMeuble(){
        DatabaseAccess bdd = new DatabaseAccess();
        try{
            Connection co = bdd.getConnection();
            PreparedStatement ps = co.prepareStatement("UPDATE equipements SET libelle= ? ,`id_pieces`= ? WHERE  id = ?");
            ps.setString(1, this.libelle);
            ps.setInt(2, this.id_pieces);
            ps.setInt(3, this.id);
            ResultSet rs = ps.executeQuery();
            System.out.println("L'objet a bien était modifié !");
        } catch (Exception e) {

        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_pieces(int id_pieces) {
        this.id_pieces = id_pieces;
    }
    @Override
    public String toString() {
        return this.libelle;
    }
}

