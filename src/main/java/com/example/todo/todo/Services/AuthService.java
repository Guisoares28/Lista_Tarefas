package com.example.todo.todo.Services;

import com.example.todo.todo.Dtos.UserRequestDto;
import com.example.todo.todo.Exceptions.UsuarioInvalidoException;
import com.example.todo.todo.Utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private final AuthenticationManager authManager;


    private final JwtUtils jwtUtils;


    public AuthService(AuthenticationManager authManager, JwtUtils jwtUtils) {
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
    }


    public String loginUsuario(UserRequestDto userRequestDto){
        try{
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userRequestDto.username(), userRequestDto.password())
            );
        }catch (AuthenticationException ex){
            throw new UsuarioInvalidoException();
        }
        //retorna o Token gerado
        return jwtUtils.gerarToken(userRequestDto.username());
    }

}
