package com.example.My_Spring_Security.repository;

import com.example.My_Spring_Security.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByPhone(String phone);
}
