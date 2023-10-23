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
        this.user = "gestionLocation";
        this.password = "Zhvh56774TiB";
        this.host = "192.168.1.124";
        this.port = 3306;
        this.database = "ar";
    }
    public Connection getConnection() throws SQLException {
        String URL = "jdbc:mysql://"+this.host+":"+this.port+"/"+this.database;
        return DriverManager.getConnection(URL, this.user, this.password);
    }
}

