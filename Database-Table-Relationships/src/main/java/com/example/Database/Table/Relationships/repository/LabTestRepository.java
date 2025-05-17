package com.example.Database.Table.Relationships.repository;

import com.example.Database.Table.Relationships.entity.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LabTestRepository extends JpaRepository<LabTest, Long> {
    Optional<LabTest> findByTestName(String name);
}
