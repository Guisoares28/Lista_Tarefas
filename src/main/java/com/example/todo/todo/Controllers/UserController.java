package com.example.todo.todo.Controllers;


import com.example.todo.todo.Dtos.UserRequestDto;
import com.example.todo.todo.Models.TaskModel;
import com.example.todo.todo.Models.UserModel;
import com.example.todo.todo.Services.UserService;
import com.example.todo.todo.Utils.TaskUtils;
import com.example.todo.todo.Utils.UserUtils;
import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Map<String, String>> salvarUsuario(@RequestBody @Valid UserRequestDto userDto) {
        UserModel userModel = UserUtils.fromUserModel(userDto);
        userService.saveUser(userModel);
        Map<String, String> mapResponse = new HashMap<>();
        mapResponse.put("Mensagem","Usuário cadastrado com sucesso.");
        return ResponseEntity.status(HttpStatus.CREATED).body(mapResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id,
                                                          @AuthenticationPrincipal UserDetails userDetails){
        UserModel userModel = userService.carregarUser(userDetails.getUsername());
        userService.deleteUser(userModel);
        return ResponseEntity.status(HttpStatus.OK)
                .body(TaskUtils.criarMap("Usuário deletado com sucesso"));

    }

    @PutMapping("/update")
    public ResponseEntity<Map<String,String>> atualizarUser(@RequestBody UserRequestDto userRequestDto){
        UserModel userModel = UserUtils.fromUserModel(userRequestDto);
        userService.updateUser(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(TaskUtils.criarMap("Usuário" +
                "atualizado com sucesso"));
    }
}
