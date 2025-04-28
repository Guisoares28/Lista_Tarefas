package com.example.todo.todo.Dtos;

import jakarta.validation.constraints.NotBlank;

public record TaskRequestDto(
        @NotBlank(message = "Descrição não pode ser vazia")
        String descricao
) {
}
