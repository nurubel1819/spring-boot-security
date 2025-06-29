package com.example.work_today.service;

import com.example.work_today.dto.DiseaseDto;
import com.example.work_today.entity.Disease;
import com.example.work_today.repository.DiseaseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UploadService {
    private final DiseaseRepo diseaseRepo;

    public Disease save_disease(Disease disease){
        try {
             System.out.println(disease);
             disease = diseaseRepo.save(disease);
            return disease;
        }catch (Exception e){
            System.out.println("Exception is ="+e.getMessage());
            return null;
        }
    }
    public List<Disease> getAllDisease(){
        return diseaseRepo.findAll();
    }
}
