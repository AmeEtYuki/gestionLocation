package com.example.test3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Inscription {
    @FXML
    public Button inscription;
    @FXML
    private TextField utilisateur;

    @FXML
    private TextField mdp;



    public static TextField getUtilisateur() {
        return null;
    }


    @FXML
    public void onInscription2(ActionEvent actionEvent) throws IOException {
        DatabaseAccess bdd = new DatabaseAccess();


        try{
            Connection con = bdd.getConnection();
            String crypt = BCrypt.hashpw(mdp.getText(), BCrypt.gensalt());
            PreparedStatement ps = con.prepareStatement("INSERT INTO login(login_pseudo, login_mdp) VALUES (?, ?)");
            ps.setString(1, utilisateur.getText());
            ps.setString(2, crypt);
            boolean resultat = ps.execute();
            HelloController.afficherAcceuil();
            /*if(resultat.next()){
                if(BCrypt.checkpw(crypt, resultat.getString(2))){
                    resultat.close();
                    Stage newWindow = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("accueil.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 500, 340);
                    newWindow.setScene(scene);
                    newWindow.show();
                }*/
            /*Stage newWindow = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("accueil.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 340);
            newWindow.setScene(scene);
            newWindow.show();*/




            con.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}
