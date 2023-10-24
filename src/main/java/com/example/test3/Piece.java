package com.example.test3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Piece {
    int id;
    float surface;
    String libelle;

    ArrayList<Equipement> lesEquipements = new ArrayList<>();

    public Piece(int id, float surface, String libelle) {
        this.id = id;
        this.surface = surface;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public float getSurface() {
        return surface;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSurface(float surface) {
        this.surface = surface;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    @Override
    public String toString() {
        return this.libelle+" surface : "+this.surface;
    }

    public ArrayList<Equipement> getLesEquipements() {
        return lesEquipements;
    }

    public void setLesEquipements(ArrayList<Equipement> lesEquipements) {
        this.lesEquipements = lesEquipements;
    }


    public void chargerMeubles() {
        DatabaseAccess bdd = new DatabaseAccess();
        lesEquipements.clear();
        try{
            Connection co = bdd.getConnection();
            PreparedStatement ps = co.prepareStatement("SELECT * from equipements WHERE id_pieces= ?");
            ps.setInt(1, this.id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //public Equipement(int id, String type, String libelle, int id_typeEquipement, int id_pieces)
                System.out.println(rs.getInt("id"));
                lesEquipements.add(new Equipement(rs.getInt("id"), rs.getString("libelle"), rs.getInt("id_pieces")));
            }
            co.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ajouterMeubles(String libelle){
        DatabaseAccess bdd = new DatabaseAccess();
        try{
            //Si tu veux tu peux descendre les valeurs vers le bas, aussi, id_typeEquipement je pense qu'on va le virer, c'était pas une bonne idée
            //de ma pars. Mais on en a besoin pour l'instant, force le à 1 stuv)
            Connection co = bdd.getConnection();
            PreparedStatement ps = co.prepareStatement("INSERT into equipements (libelle, `equipements`.`id_pieces`) values (?, ?)", new String[]{"id"});
            ps.setString(1, libelle);
            ps.setInt(2, this.id);
            ps.executeUpdate();

            ResultSet id = ps.getGeneratedKeys();
            id.next();
            if(id.next()) {
                lesEquipements.add(new Equipement(id.getInt(1), libelle, this.id));
            }

            co.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void retirerEquipement(Equipement e) {
        try {
            Connection c = new DatabaseAccess().getConnection();
            //suppression des meubles lié à la pièce puis suppression de la pièce
            PreparedStatement p2 = c.prepareStatement("DELETE FROM equipements WHERE `id` = ?");
            p2.setInt(1, e.getId());
            p2.executeUpdate();
            //Je retire la pièce de la liste.
            lesEquipements.remove(e);
            c.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void enregistrerModifPiece() {
        try{
            Connection c = new DatabaseAccess().getConnection();
            PreparedStatement p = c.prepareStatement("UPDATE pieces SET libelle = ?, surface = ? WHERE id = ?");
            p.setString(1, this.libelle);
            p.setFloat(2, this.surface);
            p.setInt(3, this.id);
            p.executeUpdate();
            c.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

