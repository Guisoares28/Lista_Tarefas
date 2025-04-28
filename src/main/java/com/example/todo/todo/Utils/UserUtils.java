package com.example.todo.todo.Utils;

import com.example.todo.todo.Dtos.UserRequestDto;
import com.example.todo.todo.Models.UserModel;
import org.springframework.stereotype.Component;


public class UserUtils {

    public static UserModel fromUserModel(UserRequestDto userDto){
        UserModel model = new UserModel();
        model.setUsername(userDto.username());
        model.setPassword(userDto.password());
        return model;
    }


}
