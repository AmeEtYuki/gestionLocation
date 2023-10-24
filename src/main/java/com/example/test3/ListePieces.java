package com.example.test3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ListePieces {
    @FXML
    ListView<Piece> listePieces;
    @FXML
    ListView<Equipement> listeMeubles;
    @FXML Button retirerPieceBoutton;
    @FXML Button retirerEquipementBoutton;
    @FXML Button ajouterEquipementBoutton;
    private static Piece piece;
    Bien leBien;
    @FXML
    public void initialize() {
        this.leBien = Accueil.getBienChoisis();
        leBien.chargerPieces();
        listePieces.getItems().addAll(leBien.getPieces());
        toggleButtons(true);
    }
    public void toggleButtons(boolean b){
        retirerPieceBoutton.setDisable(b);
        retirerEquipementBoutton.setDisable(b);
        ajouterEquipementBoutton.setDisable(b);
    }
    @FXML
    public void choixPiece() {
        piece = listePieces.getSelectionModel().getSelectedItem();
        System.out.println(piece.id);
        piece.chargerMeubles();
        System.out.println(piece.getLesEquipements());
        listeMeubles.getItems().setAll(piece.getLesEquipements());
        toggleButtons(false);
    }
    //Cette fonction retourne la pièce choisie.
    public static Piece getPiece() {
        return piece;
    }
    private void doRefresh() {

    }
    @FXML
    public void ajouterPiece() throws IOException {
            //Appel fenêtre nouvelle pièce
            Stage newWindow = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("nouvellePiece.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 200);
            newWindow.setScene(scene);
            newWindow.setTitle("Nouvelle pièce");
            newWindow.show();
    }
    @FXML
    public void retirerPiece(ActionEvent actionEvent){
            Piece p = listePieces.getSelectionModel().getSelectedItem();
            leBien.supprimerPiece(p);
            listePieces.getItems().remove(p);
            listePieces.refresh();
    }
    //Ajouter un meuble

    @FXML
    public void ajouterEquipement() throws IOException {
        //Appel fenêtre nouvelle pièce
        Stage newWindow = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("nouveauMeuble.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 200);
        newWindow.setScene(scene);
        newWindow.setTitle("Créé un équipement");
        newWindow.show();
    }

    @FXML
    public void retirerMeuble(){
        Equipement eq = listeMeubles.getSelectionModel().getSelectedItem();
        getPiece().retirerEquipement(eq);
        listeMeubles.getItems().remove(eq);
    }
    @FXML
    public void onModifEquip(ActionEvent actionEvent) throws IOException{
        Stage newWindow = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("modifEquipement.fxml"));
        Scene sc = new Scene(fxmlLoader.load(), 600, 400);
        newWindow.setScene(sc);
        newWindow.show();
    }

    @FXML
    public void onModifPiece(ActionEvent actionEvent) throws IOException{
        Stage newWindow = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("modifPiece.fxml"));
        Scene sc = new Scene(fxmlLoader.load(), 600, 400);
        newWindow.setScene(sc);
        newWindow.show();
    }
    @FXML
    public void onRefresh() {
        leBien.chargerPieces();
        listePieces.getItems().setAll(leBien.getPieces());
        listePieces.refresh();
        toggleButtons(false);
    }
    @FXML
    public void onRefresh2() {
        getPiece().chargerMeubles();
        listeMeubles.getItems().setAll(getPiece().getLesEquipements());
        toggleButtons(false);
    }


}
