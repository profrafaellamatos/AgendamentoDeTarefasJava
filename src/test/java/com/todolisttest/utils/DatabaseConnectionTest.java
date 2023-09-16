package com.todolisttest.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {
    Connection conn;

    @BeforeEach
    public void setUp() throws SQLException {
        conn = DatabaseConnection.getConnection();
    }

    @Test
    void getConnectionTest() throws SQLException{
        try {
            assertNotNull(conn); // Verifica se a conexão não é nula
            assertTrue(conn.isValid(5)); // Verifica se a conexão é válida por 5 segundos
            conn.close(); // Fecha a conexão após o teste
        } catch (SQLException e) {
            fail("Erro ao obter conexão: " + e.getMessage()); // Se ocorrer uma exceção, o teste falha
        }
    }

    @Test
    void closeConnectionTest() throws SQLException{
        try {
            DatabaseConnection.closeConnection(conn);
            assertTrue(conn.isClosed());
        } catch (SQLException e) {
            fail("Exceção não esperada: " + e.getMessage());
        }

    }
}