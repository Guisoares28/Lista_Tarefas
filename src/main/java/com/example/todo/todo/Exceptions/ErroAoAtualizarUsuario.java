package com.example.todo.todo.Exceptions;

public class ErroAoAtualizarUsuario extends RuntimeException{

    public ErroAoAtualizarUsuario(){
        super("Erro ao atualizar o usuário");
    }
}
