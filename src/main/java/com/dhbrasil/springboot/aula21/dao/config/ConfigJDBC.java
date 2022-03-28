package com.dhbrasil.springboot.aula21.dao.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigJDBC {

    private String jdbcDriver;
    private String dbUrl;
    private String nomeUsuario;
    private String senhaUsuario;

    public ConfigJDBC() {
        this.jdbcDriver = "com.mysql.cj.jdbc.Driver";
        this.dbUrl = "jdbc:mysql://localhost:3306/clinica";
        this.nomeUsuario = "root";
        this.senhaUsuario = "";
    }

    public Connection connectToDB() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, nomeUsuario, senhaUsuario);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
