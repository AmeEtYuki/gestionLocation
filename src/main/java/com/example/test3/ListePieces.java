package com.example.test3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ListePieces {
    @FXML
    ListView<Piece> listePieces;
    @FXML
    ListView<Equipement> listeMeubles;

    private static Piece piece;
    /*
    @FXML
    TableColumn<Piece, String> colonneTitre;
    @FXML
    TableColumn<Piece, Float> colonneSurface;*/
    Bien leBien;
    @FXML
    public void initialize() {
        this.leBien = Accueil.getBienChoisis();
        leBien.chargerPieces();
        //leBien.chargerMeubles();
        listePieces.getItems().addAll(leBien.getPieces());

    }
    @FXML
    public void choixPiece() {
        piece = listePieces.getSelectionModel().getSelectedItem();
        System.out.println(piece.id);
        piece.chargerMeubles();
        System.out.println(piece.getLesEquipements());
        listeMeubles.getItems().setAll(piece.getLesEquipements());
        /*for (Equipement eq : leBien.getEquipements()) {
            System.out.println(eq);
            if(eq.getId_pieces() == piece.getId()) {
                listeMeubles.getItems().add(eq);
                System.out.println(eq.toString());
            }
        }*/
    }
    //Cette fonction retourne la pièce choisie.
    public static Piece getPiece() {
        return piece;
    }
    private void doRefresh() {

    }
    @FXML
    public void ajouterPiece() throws IOException {
            //Appel fenêtre nouvelle pièce
            Stage newWindow = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("nouvellePiece.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 200);
            newWindow.setScene(scene);
            newWindow.setTitle("Nouvelle pièce");
            newWindow.show();



    }
    @FXML
    public void retirerPiece(ActionEvent actionEvent){
            Piece p = listePieces.getSelectionModel().getSelectedItem();
            leBien.supprimerPiece(p);
            listePieces.getItems().remove(p);
            listePieces.refresh();
    }
    //Ajouter un meuble

    @FXML
    public void ajouterEquipement() throws IOException {
        //Appel fenêtre nouvelle pièce
        Stage newWindow = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("nouveauMeuble.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 200);
        newWindow.setScene(scene);
        newWindow.setTitle("Créé un équipement");
        newWindow.show();
        //bah let's go essayer fin faut déjà que je fasse les bouttons PTDR mdrr cest chiant que j'ai pas acces au fichier fxml
        //T'y a accès mais t'as pas d'interface, j'trouve ça vraiment dommage, après j'pense que JavaFX est pas trop fait pour
        //Le prof il a dit qu'on utilisera jamais javaFX en entreprise donc bon..
        //J'pense qu'il y a du javafx en entreprise mais ça doit pas être courrant, contrairement à ce qu'on a fait en C# par ex
        //Ou ça doit exister pour certains projet openSource un peu paumés possible
        //je fais un truc retirerMeuble() ? Y'a pas ici ? dcp oui
    } //la méthode pour suppr est dans Bien (leBien.fonction())
    //la fonction suppMeuble il est pas la tu veux que je le fasse en rapide ? Let's go, j'ai oublier de la faire effectivement

    @FXML
    public void retirerMeuble(){
        Equipement eq = listeMeubles.getSelectionModel().getSelectedItem();
        getPiece().retirerEquipement(eq);
        listeMeubles.getItems().remove(eq);
    }
    //Faudra les interfaces, je vais les créé

    //retirer une piece

    //le mot de passe de la base de données est dans la classe DatabaseAccess au cas où,
    //avec le nom d'utilisateur et le mot de passe (le lien fonctionnera pas, faut prendre celui du .md)
    //je créé les bouttons et je les relirais à tes fonction dans la classe ListePieces,
    // il faut une fonction pour ajouter une pièce, retirer une piece (en prenant en compte les meubles)

    @FXML
    public void onModifEquip(ActionEvent actionEvent) throws IOException{
        Stage newWindow = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("modifEquipement.fxml"));
        Scene sc = new Scene(fxmlLoader.load(), 600, 400);
        newWindow.setScene(sc);
        newWindow.show();
    }

    @FXML
    public void onModifPiece(ActionEvent actionEvent) throws IOException{
        Stage newWindow = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("modifPiece.fxml"));
        Scene sc = new Scene(fxmlLoader.load(), 600, 400);
        newWindow.setScene(sc);
        newWindow.show();
    }
    @FXML
    public void onRefresh() {
        leBien.chargerPieces();
        listePieces.getItems().setAll(leBien.getPieces());
        listePieces.refresh();
    }
    @FXML
    public void onRefresh2() {
        getPiece().chargerMeubles();
        listeMeubles.getItems().setAll(getPiece().getLesEquipements());

    }


}
