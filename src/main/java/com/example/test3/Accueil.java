package com.example.test3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Accueil {
    @FXML
    public TableView<Bien> Biens;
    @FXML
    public TableColumn<Bien, Integer> champid;
    @FXML
    public TableColumn<Bien, String> colonneVille;
    @FXML
    public TableColumn<Bien, String> colonneRue;
    @FXML
    public TableColumn<Bien, Integer> colonneLibre;
    //Lecture des champs de modification

    private static Bien leBienChoisis;
    @FXML
    public TextField txtAdrr;
    @FXML TextField txtVille;
    @FXML TextField txtCP;
    @FXML DatePicker txtAnnee;
    @FXML Button bouttonEdit;
    @FXML TextField txtPrix;
    @FXML TextArea txtDesc;
    @FXML Button gererPieces;
    @FXML
    ImageView imageBien;
    //@FXML TextField

    private static int act,max;

    @FXML CheckBox boolOccupied;

    @FXML
    public void initialize() {
        //ferme la page login
        HelloApplication.getLoginPage().close();
        //chargerBien(0, 10);
        Biens.getItems().addAll(chargerBien(0, 10));
        champid.setCellValueFactory(new PropertyValueFactory<Bien, Integer>("id"));
        colonneLibre.setCellValueFactory(new PropertyValueFactory<Bien, Integer>("libre"));
        colonneRue.setCellValueFactory(new PropertyValueFactory<Bien, String>("rue"));
        colonneVille.setCellValueFactory(new PropertyValueFactory<Bien, String>("ville"));
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
        Bien leBien = Biens.getSelectionModel().getSelectedItem();
        leBien.chargerPieces();
        //leBien.chargerMeubles();
        //champid.setText(String.valueOf(leBien.getId()));
        txtAdrr.setText(leBien.getRue());
        txtVille.setText(leBien.getVille());
        txtCP.setText(leBien.getCp());
        txtDesc.setText(leBien.getDescription());
        boolOccupied.setSelected(leBien.isLibre());
        txtAnnee.setValue(leBien.getAnneeConstru().toLocalDate());
        txtPrix.setText(String.valueOf(leBien.getPrix()));
        leBienChoisis = Biens.getSelectionModel().getSelectedItem();
        gererPieces.setDisable(false);
        bouttonEdit.setDisable(false);
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
            Bien leBien = Biens.getSelectionModel().getSelectedItem();
            leBien.setAnneeConstru(Date.valueOf(txtAnnee.getValue()));
            leBien.setCp(txtCP.getText());
            leBien.setDescription(txtDesc.getText());
            leBien.setLibre(boolOccupied.isSelected());
            leBien.setRue(txtAdrr.getText());
            leBien.setVille(txtVille.getText());
            leBien.setPrix(Float.parseFloat(txtPrix.getText()));
            leBien.enregistrerModifBien();
            Biens.refresh();
            toggleTxtFields(true);
        }
    }

    @FXML
    public void voirPieces() throws IOException {
        Stage newWindow = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("listePieces.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        newWindow.setScene(scene);
        newWindow.setResizable(false);
        newWindow.show();

    }
    public static Bien getBienChoisis() {
        return leBienChoisis;
    }



    public ArrayList<Bien> chargerBien(int begin, int end) {
        DatabaseAccess bddcredentials = new DatabaseAccess();
        ArrayList<Bien> lBiens = new ArrayList<>();
        try {
            Connection connection = bddcredentials.getConnection();
            //SELECT *, count(pieces.id) as nbPieces FROM `biens` INNER JOIN pieces ON pieces.id_biens = biens.id GROUP BY biens.id LIMIT 0, 10
            PreparedStatement ps = connection.prepareStatement("SELECT *, count(pieces.id) as nbPieces FROM `biens` INNER JOIN pieces ON pieces.id_biens = biens.id GROUP BY biens.id LIMIT ?, ? ");
            ps.setInt(1, begin);
            ps.setInt(2, end);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //public biens(int id, String rue, String cp, String ville, float prix, int anneeConstru, String description, boolean libre)
                Bien bienEnChargement = new Bien(rs.getInt("id") , rs.getString("rue"), rs.getString("cp"), rs.getString("Ville"),
                        rs.getFloat("prix"), rs.getDate("anneeConstru"), rs.getString("Description"), rs.getBoolean("libre"));
                //System.out.println(bienEnChargement.getId());
                lBiens.add(bienEnChargement);

            }

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Bien b: lBiens) {
            System.out.println("Oui");
            System.out.println(b.getId());
        }
        return lBiens;
    }
}
