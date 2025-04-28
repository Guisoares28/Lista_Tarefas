package com.example.todo.todo;

import com.example.todo.todo.Models.UserModel;
import com.example.todo.todo.Services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	public class IniciarDados{
		@Bean
		public CommandLineRunner initUsuario(UserService userService, PasswordEncoder bCryptEncoder){
			return args -> {
				UserModel user = new UserModel();
				user.setUsername("guilherme");
				user.setPassword("123");
				userService.saveUser(user);
				System.out.println("Usuário inicializado com sucesso.");
			};
		}
	}

}

