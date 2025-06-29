package com.example.work_today.repository;

import com.example.work_today.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepo extends JpaRepository<Disease,Long> {
}
