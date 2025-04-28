package com.example.todo.todo.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private final String SECRET_KEY = "senhaSecreta123";

    private final Long EXPIRATION_TIME = 86400000L;


    public JwtUtils() {
    }


    public String gerarToken(String username){
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public boolean validarToken(String token){
        try{
            JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);
            return true;
        }catch (JWTVerificationException ex){
            return false;
        }
    }

    public String recuperarSubject(String token){
        return JWT.require(Algorithm.HMAC256(SECRET_KEY)).build()
                .verify(token)
                .getSubject();
    }

}
