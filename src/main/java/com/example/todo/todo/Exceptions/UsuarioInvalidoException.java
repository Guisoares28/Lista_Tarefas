package com.example.todo.todo.Exceptions;

public class UsuarioInvalidoException extends RuntimeException {

    public UsuarioInvalidoException(){
        super("Login ou Senha Invalidos");
    }

}
