package com.example.Basic.Authentication.service;

import com.example.Basic.Authentication.entity.User;
import com.example.Basic.Authentication.repo.UserDetailsRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer {
    @Bean
    public CommandLineRunner createAdminUser(UserDetailsRepo userDetailsRepo, PasswordEncoder passwordEncoder){
        return args -> {
            if(userDetailsRepo.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("kaka123"));
                admin.setRole("ROLE_ADMIN");

                userDetailsRepo.save(admin);
                System.out.println("Default admin user created");
            }
        };
    }
}
