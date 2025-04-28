package com.example.todo.todo.Controllers;

import com.example.todo.todo.Dtos.UserRequestDto;
import com.example.todo.todo.Models.UserModel;
import com.example.todo.todo.Services.AuthService;
import com.example.todo.todo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;



    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> authenticacao(@RequestBody UserRequestDto userRequestDto){
        Map<String, String> mapResponse = new HashMap<>();
        String token = authService.loginUsuario(userRequestDto);
        mapResponse.put("Token", token);
        return ResponseEntity.status(HttpStatus.OK).body(mapResponse);
    }

}
