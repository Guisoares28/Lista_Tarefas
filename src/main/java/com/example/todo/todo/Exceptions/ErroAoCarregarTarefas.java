package com.example.todo.todo.Exceptions;

public class ErroAoCarregarTarefas extends RuntimeException {

    public ErroAoCarregarTarefas(){
        super("Erro ao carregar tarefas");
    }
}
