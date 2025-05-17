package com.example.Database.Table.Relationships.repository;

import com.example.Database.Table.Relationships.entity.Lab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LabRepository extends JpaRepository<Lab, Long> {
    Optional<Lab> findByLabName(String name);
}
