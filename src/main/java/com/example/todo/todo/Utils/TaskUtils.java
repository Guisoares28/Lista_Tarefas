package com.example.todo.todo.Utils;

import com.example.todo.todo.Dtos.TaskResponseDto;
import com.example.todo.todo.Models.TaskModel;
import com.example.todo.todo.Dtos.TaskRequestDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class TaskUtils {

    public static TaskModel fromTaskModel(TaskRequestDto dto){
            TaskModel task = new TaskModel();
            task.setDescricao(dto.descricao());
            task.setStatus(false);
            return task;
    }

    public static Map<String, String> criarMap(String mensagem){
        Map<String, String> map = new HashMap<>();
        map.put("Mensagem",mensagem);
        return map;
    }

    public static List<TaskResponseDto> toListTaskResponseDto(List<TaskModel> lista){
        return lista.stream()
                .map(t -> new TaskResponseDto(
                        t.getDescricao(),
                        t.getStatus()
                ))
                .toList();
    }

}
