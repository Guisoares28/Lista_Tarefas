package com.example.todo.todo.Exceptions;

public class ErroAoAtualizarTarefas extends RuntimeException {

    public ErroAoAtualizarTarefas(){
        super("Erro ao atualizar tarefa");
    }
}
