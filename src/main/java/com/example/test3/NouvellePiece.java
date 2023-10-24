package com.example.test3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;

public class NouvellePiece {
    @FXML
    private TextField nouvPieceLabel;
    @FXML
    private TextField nouvPiece;
    @FXML
    public Button nouvPieceConfirm;

    private Bien leBien;
    @FXML
    public void initialize() {
        leBien = Accueil.getBienChoisis();
    }
   @FXML
   public void onConfirmation(ActionEvent actionEvent) throws IOException{
       String nouvPieceLab;
       nouvPieceLab = nouvPieceLabel.getText();
       String nouvPieceS;
       nouvPieceS = nouvPiece.getText();
       leBien.nouvellePiece(nouvPieceLab, Float.parseFloat(nouvPieceS));
       Stage stage = (Stage) nouvPiece.getScene().getWindow();
       stage.close();
   }


}

