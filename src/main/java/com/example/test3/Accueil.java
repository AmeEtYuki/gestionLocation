package com.example.test3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Stack;

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
    public TextField txtAdrr;
    @FXML TextField txtVille;
    @FXML TextField txtCP;
    @FXML DatePicker txtAnnee;
    @FXML Button bouttonEdit;
    @FXML TextField txtPrix;
    @FXML TextArea txtDesc;
    //@FXML TextField



    @FXML CheckBox boolOccupied;

    @FXML
    public void initialize() {
        //ferme la page login
        HelloApplication.getLoginPage().close();
        //chargerBien(0, 10);
        Biens.getItems().addAll(chargerBien(0, 10));
        champid.setCellValueFactory(new PropertyValueFactory<Biens, Integer>("id"));
        colonneLibre.setCellValueFactory(new PropertyValueFactory<Biens, Integer>("libre"));
        colonneRue.setCellValueFactory(new PropertyValueFactory<Biens, String>("rue"));
        colonneVille.setCellValueFactory(new PropertyValueFactory<Biens, String>("ville"));
        //Déverouillage des champs par défaut.
        toggleTxtFields(true);
    }
    private void toggleTxtFields(boolean wanted) {
        txtAdrr.setDisable(wanted);
        txtVille.setDisable(wanted);
        txtCP.setDisable(wanted);
        txtDesc.setDisable(wanted);
        boolOccupied.setDisable(wanted);
        txtAnnee.setDisable(wanted);
        txtPrix.setDisable(wanted);
    }


    @FXML
    public void voirBien() {
        Biens leBien = Biens.getSelectionModel().getSelectedItem();
        leBien.chargerPieces();
        leBien.chargerMeubles();
        //champid.setText(String.valueOf(leBien.getId()));
        txtAdrr.setText(leBien.getRue());
        txtVille.setText(leBien.getVille());
        txtCP.setText(leBien.getCp());
        txtDesc.setText(leBien.getDescription());
        boolOccupied.setSelected(leBien.isLibre());
        txtAnnee.setValue(leBien.getAnneeConstru().toLocalDate());
        txtPrix.setText(String.valueOf(leBien.getPrix()));
    }
    @FXML
    public void modifierBien() {
        String action = bouttonEdit.getText();
        if(action.equalsIgnoreCase("modifier")) {
            //action pour autoriser la modification.
            toggleTxtFields(false);
            bouttonEdit.setText("Sauver");
        } else if (action.equalsIgnoreCase("Sauver")) {
            bouttonEdit.setText("Modifier");
            Biens leBien = Biens.getSelectionModel().getSelectedItem();
            leBien.setAnneeConstru(Date.valueOf(txtAnnee.getValue()));
            leBien.setCp(txtCP.getText());
            leBien.setDescription(txtDesc.getText());
            leBien.setLibre(boolOccupied.isSelected());
            leBien.setRue(txtAdrr.getText());
            leBien.setVille(txtVille.getText());
            leBien.setPrix(Float.parseFloat(txtPrix.getText()));
            leBien.enregistrerModifBien();
            //Biens.getItems().remove(leBien);
            //Biens.getItems().add(leBien);
            Biens.refresh();
            toggleTxtFields(true);

        }
    }

    @FXML
    public void voirPieces() throws IOException {
        Stage newWindow = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("listePieces.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        ListePieces l = fxmlLoader.getController();
        Biens fuckyou = Biens.getSelectionModel().getSelectedItem();
        l.setLeBien(fuckyou);
        newWindow.setScene(scene);
        newWindow.show();
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
                        rs.getFloat("prix"), rs.getDate("anneeConstru"), rs.getString("Description"), rs.getBoolean("libre"));
                //System.out.println(bienEnChargement.getId());
                lBiens.add(bienEnChargement);

            }

            connection.close();
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
