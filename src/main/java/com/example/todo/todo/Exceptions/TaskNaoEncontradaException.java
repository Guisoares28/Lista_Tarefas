package com.example.todo.todo.Exceptions;

import org.springframework.scheduling.config.Task;

public class TaskNaoEncontradaException extends RuntimeException {

    public TaskNaoEncontradaException(Long id){
        super("Task não encontrada com o id - " + id);
    }

}
