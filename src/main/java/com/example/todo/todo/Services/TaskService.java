package com.example.todo.todo.Services;
import com.example.todo.todo.Exceptions.*;
import com.example.todo.todo.Models.TaskModel;
import com.example.todo.todo.Models.UserModel;
import com.example.todo.todo.Repositories.TaskRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class TaskService {


    private final TaskRepository taskRepository;

    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;

    }

    public void saveTask(TaskModel taskModel, UserDetails userDetails){
        try{
            TaskModel task = new TaskModel();
            UserModel user = userService.carregarUser(userDetails.getUsername());
            task.setDescricao(taskModel.getDescricao());
            task.setStatus(false);
            task.setUsuario(user);
            taskRepository.save(task);
        }catch (Exception ex){
            throw new ErroAoSalvarTarefa();
        }

    }


    public List<TaskModel> buscarTasks(UserDetails userDetails){
        try{
            UserModel userModel = userService.carregarUser(userDetails.getUsername());
            return taskRepository.findByUsuario(userModel);
        }catch (Exception ex){
            throw new ErroAoCarregarTarefas();
        }

    }

    public List<TaskModel> buscarPorStatus(UserDetails userDetails, Boolean status){
        try{
            UserModel userModel = userService.carregarUser(userDetails.getUsername());
            return taskRepository.findByUsuarioAndStatus(userModel, status);
        }catch (Exception ex){
            throw new ErroAoCarregarTarefas();
        }

    }

    public List<TaskModel> carregarTasks(UserDetails userDetails, Boolean status){
        List<TaskModel> lista;
        if(status != null){
            lista = this.buscarPorStatus(userDetails, status);
        }else{
            lista = this.buscarTasks(userDetails);
        }
        return lista;
    }

    public TaskModel buscarTaskPorId(UserDetails userDetails, Long id){
        UserModel userModel = userService.carregarUser(userDetails.getUsername());
        TaskModel task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNaoEncontradaException(id));
        if(!userModel.getId().equals(task.getUsuario().getId())){
            throw new TaskNaoEncontradaException(id);
        }
        return task;
    }

    public void UpdateTask(Long id, UserDetails userDetails){
        try{
            TaskModel task = this.buscarTaskPorId(userDetails, id);
            task.setStatus(Boolean.FALSE.equals(task.getStatus()));
            taskRepository.save(task);
        }catch (Exception ex){
            throw new ErroAoAtualizarTarefas();
        }
    }


    public void deletarTask(Long id, UserDetails userDetails){
        try{
            UserModel user = userService.carregarUser(userDetails.getUsername());
            TaskModel task = this.buscarTaskPorId(userDetails,id);
            if(!user.getId().equals(task.getUsuario().getId())){
                throw new ErroAoDeletarTarefaComIdException();
            }
            taskRepository.deleteById(id);
        }catch (Exception ex){
            throw new ErroAoDeletarTarefaComIdException();
        }

    }

    public Boolean existeTask(Long id){
        return taskRepository.existsById(id);
    }
}
