package com.todolisttest.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/todolist_test";
    private static final String USER = "seu_usuario"; //Informe seu usuário
    private static final String PASSWORD = "sua_senha"; //informe sua senha

    public static Connection getConnection() throws SQLException {
        System.out.println("Conexão com o BD realizada com sucesso!");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection conn) throws SQLException{
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Conexão com o BD encerrada.");
    }
}
