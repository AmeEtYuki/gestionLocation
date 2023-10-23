package com.example.test3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ErreurBDD {
    @FXML
    Button ok;
    @FXML
    public void onOkClick() {
        Stage thisWin = (Stage) ok.getScene().getWindow();
        thisWin.close();
    }
}
