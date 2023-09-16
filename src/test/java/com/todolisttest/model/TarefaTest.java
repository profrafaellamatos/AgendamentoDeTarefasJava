package com.todolisttest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TarefaTest {
    Tarefa tarefa1, tarefa2;

    @BeforeEach
    public void setUp(){
        tarefa1 = new Tarefa();
        tarefa2 = new Tarefa(2,"Fazer compras", false);
    }

    @Test
    void getIdTest() {
        assertEquals(2, tarefa2.getId());
    }

    @Test
    void setIdTest() {
        tarefa1.setId(1);
        assertEquals(1, tarefa1.getId());

    }

    @Test
    void getDescricaoTest() {
        assertEquals("Fazer compras", tarefa2.getDescricao());
    }

    @Test
    void setDescricaoTest() {
        tarefa1.setDescricao("Estudar");
        assertEquals("Estudar", tarefa1.getDescricao());
    }

    @Test
    void isFinalizadaTest() {
        assertFalse(tarefa2.isFinalizada());
    }

    @Test
    void setFinalizadaTest() {
        tarefa1.setFinalizada(true);
        assertTrue(tarefa1.isFinalizada());
    }
}