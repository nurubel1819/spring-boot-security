package com.example.Database.Table.Relationships.service;

import com.example.Database.Table.Relationships.entity.Lab;
import com.example.Database.Table.Relationships.repository.LabRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LabService {
    private final LabRepository labRepository;

    public Lab uploadNewLab(Lab lab){
        try {
            return labRepository.save(lab);
        }catch (Exception e){
            System.out.println("Exception in LabService uploadNewLab = ");
            e.printStackTrace();
            return null;
        }
    }
    public Lab getLabByName(String name){
        return labRepository.findByLabName(name).orElse(null);
    }
}
