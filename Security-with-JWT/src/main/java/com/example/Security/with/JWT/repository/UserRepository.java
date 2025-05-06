package com.example.Security.with.JWT.repository;

import com.example.Security.with.JWT.entity.Role;
import com.example.Security.with.JWT.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}
