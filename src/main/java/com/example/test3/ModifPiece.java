package com.example.test3;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifPiece {
    @FXML
    TextField txtLibelle;
    @FXML
    TextField txtSurface;
    private Piece p;
    @FXML
    public void initialize() {
        p = ListePieces.getPiece();
        txtLibelle.setText(p.getLibelle());
        txtSurface.setText(String.valueOf(p.getSurface()));
    }
    @FXML
    public void onConfirm() {
        p.setLibelle(txtLibelle.getText());
        p.setSurface(Float.parseFloat(txtSurface.getText()));
        p.enregistrerModifPiece();
        Stage s = (Stage) txtSurface.getScene().getWindow();
        s.close();
    }
}
