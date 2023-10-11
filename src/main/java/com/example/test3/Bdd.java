package com.example.test3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bdd {
    private String password;
    private String user;
    private String host;
    private int port;
    private String database;
    public Bdd() {
        this.user = "full";
        this.password = "flemme";
        this.host = "172.19.0.100";
        this.port = 3306;
        this.database = "ar";
    }
    public Connection getConnection() throws SQLException {
        String URL = "jdbc:mysql://"+this.host+":"+this.port+"/"+this.database;
        return DriverManager.getConnection(URL, this.user, this.password);
    }
}

