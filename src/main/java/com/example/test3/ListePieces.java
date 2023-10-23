package com.example.test3;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class ListePieces {
    @FXML
    TableView<Piece> listePieces;
    @FXML
    TableView<Meuble> listeMeubles;
    Biens leBien;
    public void setLeBien(Biens b) {
        this.leBien = b;
    }
    @FXML
    public void initialize() {
        leBien.getPieces();
        listePieces.getItems().addAll(leBien.getPieces());
    }



}
