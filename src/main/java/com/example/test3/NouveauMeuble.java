package com.example.test3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;

public class NouveauMeuble {
    @FXML
    private TextField nouvMeubleLab;
    @FXML
    private TextField nouvMeuble;
    @FXML
    public Button nouvMeubleConfirm;
    @FXML
    public Button nouvMeubleCancel;

    private Bien leBien;
    private Piece laPiece;
    @FXML
    public void initialize() {
        leBien = Accueil.getBienChoisis();
        laPiece = ListePieces.getPiece();
    }

    @FXML
    public void onConfirmation(ActionEvent actionEvent) throws IOException{
        String nouvMeubleLabelle;
        nouvMeubleLabelle = nouvMeubleLab.getText();
        laPiece.ajouterMeubles(nouvMeubleLabelle);
        Stage stage = (Stage) nouvMeubleConfirm.getScene().getWindow();
        stage.close();
    }


}
