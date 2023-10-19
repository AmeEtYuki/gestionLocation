package com.example.test3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    Scene scene;
    static private Stage LoginPage;
    @Override
    public void start(Stage rae) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 480);
        stage.setTitle("Connection");
        stage.setScene(scene);
        stage.onShowingProperty();
        stage.show();
        LoginPage=stage;
    }
    public static Stage getLoginPage() {
        return LoginPage;
    }

    public static void main(String[] args) {
        launch();
    }
}