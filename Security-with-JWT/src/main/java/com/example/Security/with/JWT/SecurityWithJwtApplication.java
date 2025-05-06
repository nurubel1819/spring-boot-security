package com.example.Security.with.JWT;

import com.example.Security.with.JWT.entity.Role;
import com.example.Security.with.JWT.entity.User;
import com.example.Security.with.JWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecurityWithJwtApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SecurityWithJwtApplication.class, args);
	}

	public void run(String... args){
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if(adminAccount==null){
			User user = new User();
			user.setEmail("nurubel70@gmail.com");
			user.setUserName("Rubel");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("123"));
			userRepository.save(user);
		}
	}
}
