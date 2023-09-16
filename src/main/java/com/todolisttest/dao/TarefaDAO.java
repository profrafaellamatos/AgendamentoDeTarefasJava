package com.todolisttest.dao;

import com.todolisttest.model.Tarefa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {
    private Connection conn;

    public TarefaDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean cadastrarTarefa(Tarefa tarefa) {
        PreparedStatement stmt = null;
        boolean tarefaCadastradoComSucesso = false;

        try {
            String sql = "INSERT INTO tarefas (descricao, finalizada) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);

            // Preenche os valores do PreparedStatement com os dados do paciente
            stmt.setString(1, tarefa.getDescricao());
            stmt.setBoolean(2, tarefa.isFinalizada());

            // Executa a inserção no banco de dados
            int rowsAffected = stmt.executeUpdate();

            // Verifica se o paciente foi cadastrado com sucesso
            tarefaCadastradoComSucesso = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha o PreparedStatement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return tarefaCadastradoComSucesso;
    }


    public List<Tarefa> obterTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM tarefas";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                boolean finalizada = rs.getBoolean("finalizada");

                Tarefa tarefa = new Tarefa(id, descricao, finalizada);
                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Feche os recursos (ResultSet, PreparedStatement)
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return tarefas;
    }

    public Tarefa obterTarefaPorId(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Tarefa tarefa = null;

        try {
            String sql = "SELECT * FROM tarefas WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                String descricao = rs.getString("descricao");
                boolean finalizada = rs.getBoolean("finalizada");

                tarefa = new Tarefa(id, descricao, finalizada);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Feche os recursos (ResultSet, PreparedStatement)
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return tarefa;
    }


    public void atualizarTarefa(Tarefa tarefa) {
        PreparedStatement stmt = null;

        try {
            String sql = "UPDATE tarefas SET descricao = ?, finalizada = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);

            // Preenche os valores do PreparedStatement com os dados da tarefa
            stmt.setString(1, tarefa.getDescricao());
            stmt.setBoolean(2, tarefa.isFinalizada());
            stmt.setInt(3, tarefa.getId()); // Supondo que você tenha um campo 'id' na classe Tarefa

            // Executa a atualização no banco de dados
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha o PreparedStatement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void excluirTarefa(int tarefaId) {
        PreparedStatement stmt = null;

        try {
            String sql = "DELETE FROM tarefas WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, tarefaId);

            // Executa a exclusão no banco de dados
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha o PreparedStatement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
