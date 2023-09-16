package com.todolisttest;

import com.todolisttest.dao.TarefaDAO;
import com.todolisttest.model.Tarefa;
import com.todolisttest.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TodolisttestApp {
    public static void main(String[] args) {
        Connection conn = null;



        try {
            conn = DatabaseConnection.getConnection();

            Tarefa novaTarefa = new Tarefa("Estudar", true);

            TarefaDAO tarefaDAO = new TarefaDAO(conn);

            boolean cadastradoComSucesso = tarefaDAO.cadastrarTarefa(novaTarefa);

            if (cadastradoComSucesso) {
                System.out.println("Nova tarefa com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar nova tarefa.");
            }

            // Fechar a conexão com o banco de dados
            DatabaseConnection.closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
