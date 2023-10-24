package com.example.test3;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;

public class NouveauBien {
    @FXML
    TextField txtVille;
    @FXML
    TextField txtAdresse;
    @FXML
    TextField txtCP;
    @FXML
    TextArea txtDescription;
    @FXML
    TextField txtPrix;
    @FXML
    DatePicker selectDate;
    @FXML
    CheckBox isOccupied;
    @FXML
    public void onConfirm() {
        Bien b = new Bien(txtAdresse.getText(), txtCP.getText(), txtVille.getText(), Float.parseFloat(txtPrix.getText()), Date.valueOf(selectDate.getValue()), txtDescription.getText(), isOccupied.isSelected());
        Stage stage = (Stage) txtDescription.getScene().getWindow();
        stage.close();
    }
}
