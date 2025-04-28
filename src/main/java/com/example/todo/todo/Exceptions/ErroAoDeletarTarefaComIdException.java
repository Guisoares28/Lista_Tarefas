package com.example.todo.todo.Exceptions;

public class ErroAoDeletarTarefaComIdException extends RuntimeException {
    public ErroAoDeletarTarefaComIdException(){
        super("Você não tem autorização para deletar está Tarefa");
    }
}
