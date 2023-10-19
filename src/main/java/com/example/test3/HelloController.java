package com.example.test3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {
    public Button monBoutonConnecter;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField monID;
    @FXML
    private TextField monMDP;
    @FXML
    protected void onHelloButtonClick() throws IOException {
        Bdd bdd = new Bdd();

        try{
            Connection con = bdd.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT login_pseudo, login_mdp FROM login where login_pseudo = ? ");
            ps.setString(1, monID.getText());
            ResultSet resultat = ps.executeQuery();
            if(resultat.next()){
                if(BCrypt.checkpw(monMDP.getText(), resultat.getString("login_mdp")) || true){
                    resultat.close();
                    Stage newWindow = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("accueil.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1069, 500);
                    newWindow.setScene(scene);
                    newWindow.setTitle("Votre meilleur appli de gestion");
                    newWindow.show();
                    //Stage tej = HelloApplication.getPtn();
                    //tej.close();
                }else{
                    resultat.close();
                    Stage newWindow = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("erreur.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 100, 200);
                    newWindow.setScene(scene);
                    newWindow.show();

                }
            }
            con.close();
        }catch (SQLException e){
            Stage err = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("erreurBDD.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1069, 500);
            err.setScene(scene);
            err.setTitle("Erreur de connexion");
            err.show();
            //e.getErrorCode();
            //throw new RuntimeException(e);

        }

    }
}