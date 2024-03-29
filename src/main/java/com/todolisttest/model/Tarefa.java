package com.todolisttest.model;

public class Tarefa {
    private int id;
    private String descricao;
    private boolean finalizada;

    public Tarefa() {
    }

    public Tarefa(int id, String descricao, boolean finalizada) {
        this.id = id;
        this.descricao = descricao;
        this.finalizada = finalizada;
    }

    public Tarefa(String descricao, boolean finalizada) {
        this.descricao = descricao;
        this.finalizada = finalizada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }
}
