package com.example.test3;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Accueil {
    @FXML
    public TableView<Biens> Biens;
    @FXML
    public TableColumn<Biens, Integer> champid;
    @FXML
    public TableColumn<Biens, String> colonneVille;
    @FXML
    public TableColumn<Biens, String> colonneRue;
    @FXML
    public TableColumn<Biens, Integer> colonneLibre;
    //Lecture des champs de modification

    @FXML
    public void initialize() {
        //chargerBien(0, 10);
        Biens.getItems().addAll(chargerBien(0, 10));
        champid.setCellValueFactory(new PropertyValueFactory<Biens, Integer>("id"));
        colonneLibre.setCellValueFactory(new PropertyValueFactory<Biens, Integer>("libre"));
        colonneRue.setCellValueFactory(new PropertyValueFactory<Biens, String>("rue"));
        colonneVille.setCellValueFactory(new PropertyValueFactory<Biens, String>("ville"));
        //Déverouillage des champs par défaut.

    }

    @FXML
    public void voirBien() {

    }
    @FXML
    public void modifierBien() {

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
