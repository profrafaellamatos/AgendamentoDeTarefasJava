package com.todolisttest.dao;

import com.todolisttest.model.Tarefa;
import com.todolisttest.utils.DatabaseConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TarefaDAOIntegrationTest {
    Connection conn;

    @BeforeEach
    public void setUp() {
        //Estabelecer conexão com o BD antes de cada teste
        {
            try {
                conn = DatabaseConnection.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @AfterEach
    public void tearDown() {
        // Limpe a tabela de tarefas após cada teste
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM tarefas");
            stmt.close();
            DatabaseConnection.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método auxiliar para inserir tarefas de teste no banco de dados
    private void inserirTarefaTeste(String descricao, boolean finalizada) {
        TarefaDAO tarefaDAO = new TarefaDAO(conn);
        Tarefa tarefa = new Tarefa(descricao, finalizada);
        tarefaDAO.cadastrarTarefa(tarefa);
    }

    @Test
    @DisplayName("Deveria estar cadastrando tarefas na tabela 'tarefas'")
    void cadastrarTarefaTest() {
        TarefaDAO tarefaDAO= new TarefaDAO(conn);;
        Tarefa tarefa = new Tarefa("Limpar a casa", false);

        boolean tarefaCadastrada = tarefaDAO.cadastrarTarefa(tarefa);

        assertTrue(tarefaCadastrada);
    }


    @Test
    @DisplayName("Deveria estar recuperando as tarefas cadastradas na tabela 'tarefas'")
    void obterTarefasTest(){
        TarefaDAO tarefaDAO= new TarefaDAO(conn);
        inserirTarefaTeste("Tarefa 1", false);
        inserirTarefaTeste("Tarefa 2", true);
        inserirTarefaTeste("Tarefa 3", false);

        List<Tarefa> tarefas = tarefaDAO.obterTarefas();

        assertFalse(tarefas.isEmpty());
        assertEquals(3, tarefas.size());
    }



    @Test
    @DisplayName("Deveria estar recuperando uma tarefa da tabela 'tarefas' com base no ID")
    void obterTarefaPorIdTest(){
        TarefaDAO tarefaDAO = new TarefaDAO(conn);
        inserirTarefaTeste("Tarefa de Teste 1", false);
        List<Tarefa> tarefas = tarefaDAO.obterTarefas();
        int tarefaId = tarefas.get(0).getId();

        Tarefa tarefaRecuperada = tarefaDAO.obterTarefaPorId(tarefaId);

        assertNotNull(tarefaRecuperada);
        assertEquals(1, tarefas.size());
        assertEquals("Tarefa de Teste 1", tarefaRecuperada.getDescricao());
        assertFalse(tarefas.isEmpty());
        assertFalse(tarefaRecuperada.isFinalizada());
    }


    @Test
    @DisplayName("Deveria estar atualizando uma tarefa da tabela 'tarefas'")
    void atualizarTarefasTest(){
        TarefaDAO tarefaDAO = new TarefaDAO(conn);
        inserirTarefaTeste("Tarefa de Teste 1", false);
        List<Tarefa> tarefas = tarefaDAO.obterTarefas();
        int tarefaId = tarefas.get(0).getId();
        Tarefa tarefaAtualizada = new Tarefa(tarefaId, "Tarefa Atualizada", true);

        tarefaDAO.atualizarTarefa(tarefaAtualizada);
        Tarefa tarefaRecuperada = tarefaDAO.obterTarefaPorId(tarefaId);

        assertNotNull(tarefaAtualizada);
        assertFalse(tarefas.isEmpty());
        assertEquals(1, tarefas.size());
        assertEquals(tarefaAtualizada.getDescricao(), tarefaRecuperada.getDescricao());
    }

    @Test
    @DisplayName("Deveria estar excluindo uma tarefa da tabela 'tarefas' pelo id")
    void excluirTarefasTest(){
        TarefaDAO tarefaDAO = new TarefaDAO(conn);
        inserirTarefaTeste("Tarefa de Teste 1", false);
        List<Tarefa> tarefas = tarefaDAO.obterTarefas();
        int tarefaId = tarefas.get(0).getId();

        tarefaDAO.excluirTarefa(tarefaId);
        tarefas = tarefaDAO.obterTarefas();

        assertNull(tarefaDAO.obterTarefaPorId(tarefaId));
        assertTrue(tarefas.isEmpty());
    }
}