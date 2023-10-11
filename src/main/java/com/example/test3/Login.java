package com.example.test3;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private int id;
    private String login_pseudo;
    private String login_mdp;



    /*public void connection(){
        Bdd bdd = new Bdd();
        try{
            Connection con = bdd.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT login_pseudo, login_mdp FROM login where login_pseudo = ? ");
            ps.setString(1, login_pseudo);
            ResultSet resultat = ps.executeQuery();
            if(resultat.next()){
                if(BCrypt.checkpw(entered)){

                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }



    }
*/
}
