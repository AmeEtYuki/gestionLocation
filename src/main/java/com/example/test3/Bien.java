package com.example.test3;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Bien {
    private int id;
    private String rue;
    private String cp;
    private String ville;
    private float prix;
    private Date anneeConstru;
    private String description;
    private boolean libre;
    ArrayList<Piece> pieces = new ArrayList<>();
    ArrayList<Equipement> equipements = new ArrayList<>();
    public Bien(int id, String rue, String cp, String ville, float prix, Date anneeConstru, String description, boolean libre) {
        this.id = id;
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
        this.prix = prix;
        this.anneeConstru = anneeConstru;
        this.description = description;
        this.libre = libre;
    }
    public Bien(String rue, String cp, String ville, float prix, Date anneeConstru, String description, boolean libre) {
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
        this.prix = prix;
        this.anneeConstru = anneeConstru;
        this.description = description;
        this.libre = libre;
        nouveauBien();
    }

    public String getRue() {
        return rue;
    }

    public int getId() {
        return id;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getAnneeConstru() {
        return anneeConstru;
    }

    public void setAnneeConstru(Date anneeConstru) {
        this.anneeConstru = anneeConstru;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public Collection<Equipement> getEquipements() {
        return equipements;
    }

    public void setEquipements(ArrayList<Equipement> equipements) {
        this.equipements = equipements;
    }

    public boolean isLibre() {
        return libre;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
    }

    //Ajout de meubles + vérification

    public void enregistrerModifBien() {
        //UPDATE `biens` SET `id`='[value-1]',`rue`='[value-2]',`cp`='[value-3]',`ville`='[value-4]',`prix`='[value-5]',`anneeConstru`='[value-6]',`description`='[value-7]',`libre`='[value-8]' WHERE `id`=?
        String sql = "UPDATE `biens` SET `rue`=?,`cp`=?,`ville`=?,`prix`=?,`anneeConstru`=?,`description`=?,`libre`=? WHERE `id`=?";
        try {
            Connection c = new DatabaseAccess().getConnection();
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, this.rue);
            p.setString(2, this.cp);
            p.setString(3, this.ville);
            p.setFloat(4, this.prix);
            p.setDate(5, this.anneeConstru);
            p.setString(6, this.description);
            p.setBoolean(7, this.libre);
            p.setInt(8, this.id);
            p.executeUpdate();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void nouveauBien() {
        //new String[]{"id"} pour récup le dernier ID généré, à rajouter après la requête
        //PreparedStagement p = ....(sql,new String[]{"id"})
        try{
            Connection c = new DatabaseAccess().getConnection();
            PreparedStatement p = c.prepareStatement("INSERT into biens (rue, cp, ville, prix, anneeConstru, description, libre) values (?, ?, ?, ?, ?, ?, ?)", new String[]{"id"});
            p.setString(1, this.rue);
            p.setString(2, this.cp);
            p.setString(3, this.ville);
            p.setFloat(4, this.prix);
            p.setDate(5, this.anneeConstru);
            p.setString(6, this.description);
            p.setBoolean(7, this.libre);
            p.executeUpdate();
            ResultSet rs = p.getGeneratedKeys();
            rs.next();
            this.id = rs.getInt(1);
            c.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void nouvellePiece(String libelle, float surface)
   {
        try {
            Connection c = new DatabaseAccess().getConnection();
            Scanner s = new Scanner(System.in);
            //INSERT INTO `pieces` (`id`, `surface`, `libelle`, `id_biens`) VALUES (NULL, '30', 'Cuisine', '101');
            PreparedStatement p = c.prepareStatement("INSERT INTO `pieces` (`id`, `surface`, `libelle`, `id_biens`) VALUES (NULL, ?, ?, ?)", new String[]{"id"});
            p.setFloat(1, surface);
            p.setString(2, libelle);
            p.setInt(3, this.id);
            p.executeUpdate();
            ResultSet rs = p.getGeneratedKeys();

            if(rs.next())
            {
                int last_inserted_id = rs.getInt(1);
                System.out.println(last_inserted_id);
                //création de l'entité dans la liste des pieces du bien.
                pieces.add(new Piece(last_inserted_id, surface, libelle));
            }

            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void supprimerPiece(Piece pi) {
        try {
            Connection c = new DatabaseAccess().getConnection();
            //suppression des meubles lié à la pièce puis suppression de la pièce
            PreparedStatement p2 = c.prepareStatement("DELETE FROM equipements WHERE `id_pieces` = ?");
            p2.setInt(1, pi.getId());
            p2.executeUpdate();
            //Suppression de la pièce elle même
            PreparedStatement p = c.prepareStatement("DELETE FROM pieces WHERE `id` = ?");
            p.setFloat(1, pi.getId());
            p.executeUpdate();
            //Je retire la pièce de la liste.
            pieces.remove(p);
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void chargerPieces(){
        pieces.clear();
        DatabaseAccess bdd = new DatabaseAccess();

        try {
            Connection co = bdd.getConnection();
            PreparedStatement ps = co.prepareStatement("SELECT * from pieces WHERE id_biens = ?");
            ps.setInt(1, this.id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pieces.add(new Piece(rs.getInt("id"), rs.getFloat("surface"), rs.getString("libelle")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void chargernbPieces() {
        chargerPieces();
    }

    @Override
    public String toString() {
        return
                "(" + id + ")'" + rue + " | " + ville +
                ", pieces :" + pieces.size() +
                ", meubles :" + equipements.size() +
                '}';
    }
}
