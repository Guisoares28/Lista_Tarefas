package com.example.todo.todo.Services;

import com.example.todo.todo.Exceptions.ErroAoAtualizarUsuario;
import com.example.todo.todo.Exceptions.ErroAoSalvarUsuario;
import com.example.todo.todo.Models.UserModel;
import com.example.todo.todo.Repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepository userRepository;


    private final PasswordEncoder bCrypPasswordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder bCrypPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCrypPasswordEncoder = bCrypPasswordEncoder;
    }

    public void saveUser(UserModel user){
        try{
            if(userRepository.existsByUsername(user.getUsername())){
                throw new ErroAoSalvarUsuario();
            }
            user.setPassword(bCrypPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }catch(Exception ex){
            throw new ErroAoSalvarUsuario();
        }

    }

    public UserModel carregarUser(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public void updateUser(UserModel userModel){
        try{
            UserModel user  = this.carregarUser(userModel.getUsername());
            user.setUsername(userModel.getUsername());
            user.setPassword(bCrypPasswordEncoder.encode(userModel.getPassword()));
            userRepository.save(user);
        }catch (Exception ex){
            throw new ErroAoAtualizarUsuario();
        }

    }

}
