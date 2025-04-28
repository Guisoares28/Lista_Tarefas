package com.example.todo.todo.Controllers;

import com.example.todo.todo.Dtos.TaskResponseDto;
import com.example.todo.todo.Models.TaskModel;
import com.example.todo.todo.Services.TaskService;
import com.example.todo.todo.Dtos.TaskRequestDto;
import com.example.todo.todo.Utils.TaskUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/task")
public class TaskController {


    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Map<String, String>> salvarTask(@RequestBody @Valid TaskRequestDto taskRequestDto,
                                                         @AuthenticationPrincipal UserDetails userDetails){
        taskService.saveTask(TaskUtils.fromTaskModel(taskRequestDto), userDetails);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TaskUtils.criarMap("Tarefa Cadastrada com Sucesso"));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<TaskResponseDto>> buscarTodasTasks(@RequestParam(required = false) Boolean status,
                                                            @AuthenticationPrincipal UserDetails userDetails){
       List<TaskModel> tasks = taskService.carregarTasks(userDetails,status);
        List<TaskResponseDto> listResponseDto = TaskUtils.toListTaskResponseDto(tasks);
        return ResponseEntity.status(HttpStatus.OK).body(listResponseDto);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Map<String,String>> mudarStatus(@PathVariable Long id,
                                              @AuthenticationPrincipal UserDetails userDetails){
        taskService.UpdateTask(id, userDetails);
        return ResponseEntity.status(HttpStatus.OK)
                .body(TaskUtils.criarMap("Status atualizado com sucesso"));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarTask(@PathVariable Long id,
                                                           @AuthenticationPrincipal UserDetails userDetails){
        taskService.deletarTask(id,userDetails);
        return ResponseEntity.status(HttpStatus.OK)
                .body(TaskUtils.criarMap("Tarefa deletada com sucesso"));


    }

}
