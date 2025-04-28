package com.example.todo.todo.Exceptions;

public class ListaVaziaException extends RuntimeException {

    public ListaVaziaException(){
        super("Não existe Tarefas Registradas");
    }

}
