package com.example.test3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ErreurBDD {

    @FXML
    public void onOkClick() {
        Stage thisWin = (Stage) Stage.getWindows();
        thisWin.close();
    }
}
