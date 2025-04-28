package com.example.todo.todo.ExceptionHandler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.todo.todo.Exceptions.*;
import com.example.todo.todo.Utils.TaskUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ManipuladorDeExcecoes {

    @ExceptionHandler(ErroAoSalvarTarefa.class)
    public ResponseEntity<Map<String, String>> ErroAoSalvarHandler(ErroAoSalvarTarefa ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TaskUtils.criarMap(
                ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> campoVazioHandler(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ":" + erro.getDefaultMessage())
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    @ExceptionHandler(ListaVaziaException.class)
    public ResponseEntity<Map<String, String>> listaVaziaHandler(ListaVaziaException ex){
        return ResponseEntity.status(HttpStatus.OK).body(TaskUtils.criarMap(
                ex.getMessage()));
    }

    @ExceptionHandler(TaskNaoEncontradaException.class)
    public ResponseEntity<Map<String, String>> taskNaoEncontradaHandler(TaskNaoEncontradaException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TaskUtils.criarMap(
                ex.getMessage()));
    }

    @ExceptionHandler(ErroAoSalvarUsuario.class)
    public ResponseEntity<Map<String, String>> ErroAoSalvarUserHandler(ErroAoSalvarUsuario ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TaskUtils.criarMap(
                ex.getMessage()));
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<Map<String, String>> JWTVerificationExceptionHandler(JWTVerificationException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TaskUtils.criarMap(
                ex.getMessage()));
    }

    @ExceptionHandler(UsuarioInvalidoException.class)
    public ResponseEntity<Map<String, String>> UsuarioInvalidoExceptionHandler(UsuarioInvalidoException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(TaskUtils.criarMap(
                ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> UserNameNotFoundExceptionHandler(BadCredentialsException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(TaskUtils.criarMap(ex.getMessage()));
    }

    @ExceptionHandler(ErroAoCarregarTarefas.class)
    public ResponseEntity<Map<String, String>> ErroAoCarregarHandler(ErroAoCarregarTarefas ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TaskUtils.criarMap(ex.getMessage()));
    }

    @ExceptionHandler(ErroAoAtualizarTarefas.class)
    public ResponseEntity<Map<String, String>> ErroAoAtualizaHandler(ErroAoAtualizarTarefas ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TaskUtils.criarMap(ex.getMessage()));
    }

}
