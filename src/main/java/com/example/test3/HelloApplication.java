package com.example.test3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    Scene scene;
    static private Stage ptn;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 480);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        ptn=stage;
    }
    public static Stage getPtn() {
        return ptn;
    }

    public static void main(String[] args) {
        launch();
    }
}