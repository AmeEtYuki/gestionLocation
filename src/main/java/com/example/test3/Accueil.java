package com.example.test3;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Accueil {
    @FXML
    public ListView Biens;

    @FXML
    public void initialize() {
        //chargerBien(0, 10);
        Biens.getItems().addAll(chargerBien(0, 10));


    }
    public ArrayList<Biens> chargerBien(int begin, int end) {
        DatabaseAccess bddcredentials = new DatabaseAccess();
        ArrayList<Biens> lBiens = new ArrayList<>();
        try {
            Connection connection = bddcredentials.getConnection();
            //SELECT *, count(pieces.id) as nbPieces FROM `biens` INNER JOIN pieces ON pieces.id_biens = biens.id GROUP BY biens.id LIMIT 0, 10
            PreparedStatement ps = connection.prepareStatement("SELECT *, count(pieces.id) as nbPieces FROM `biens` INNER JOIN pieces ON pieces.id_biens = biens.id GROUP BY biens.id LIMIT ?, ? ");
            ps.setInt(1, begin);
            ps.setInt(2, end);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //public biens(int id, String rue, String cp, String ville, float prix, int anneeConstru, String description, boolean libre)
                Biens bienEnChargement = new Biens(rs.getInt("id") , rs.getString("rue"), rs.getString("cp"), rs.getString("Ville"),
                        rs.getFloat("prix"), rs.getDate("anneeConstru").getYear(), rs.getString("Description"), rs.getBoolean("libre"));
                System.out.println(bienEnChargement.getId());
                lBiens.add(bienEnChargement);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Biens b: lBiens) {
            System.out.println("Oui");
            System.out.println(b.getId());
        }
        return lBiens;
    }
}
