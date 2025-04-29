package com.example.todo.todo.Repositories;

import com.example.todo.todo.Models.TaskModel;
import com.example.todo.todo.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {

    List<TaskModel> findByUsuarioAndStatus(UserModel userModel, Boolean status);

    List<TaskModel> findByUsuario(UserModel userModel);
}
