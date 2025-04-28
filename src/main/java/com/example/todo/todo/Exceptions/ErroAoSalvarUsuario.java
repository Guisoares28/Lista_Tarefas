package com.example.todo.todo.Exceptions;

public class ErroAoSalvarUsuario extends RuntimeException{

    public ErroAoSalvarUsuario(){
        super("Erro ao salvar usuario");
    }

}
