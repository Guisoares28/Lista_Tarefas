package com.example.todo.todo.Dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(
        @NotBlank(message = "Usuário não pode ser vazio")
        String username,
        @NotBlank(message = "Senha não pode ser vazia")
        String password
) {
}
