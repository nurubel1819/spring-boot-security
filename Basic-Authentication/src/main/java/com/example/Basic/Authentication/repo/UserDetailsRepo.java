package com.example.Basic.Authentication.repo;

import com.example.Basic.Authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepo extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
