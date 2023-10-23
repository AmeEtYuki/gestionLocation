package com.example.test3;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListePieces {
    @FXML
    ListView<Piece> listePieces;
    @FXML
    ListView<Equipement> listeMeubles;
    /*
    @FXML
    TableColumn<Piece, String> colonneTitre;
    @FXML
    TableColumn<Piece, Float> colonneSurface;*/
    Bien leBien;
    @FXML
    public void initialize() {
        this.leBien = Accueil.getBienChoisis();
        leBien.chargerPieces();
        leBien.chargerMeubles();
        listePieces.getItems().addAll(leBien.getPieces());
    }
    @FXML
    public void choixPiece() {
        Piece piece = listePieces.getSelectionModel().getSelectedItem();
        for (Equipement eq : leBien.getEquipements()) {
            if(eq.getId_pieces() == piece.getId()) {
                listeMeubles.getItems().add(eq);
            }
        }
    }



}
