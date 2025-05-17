package com.example.Database.Table.Relationships.service;

import com.example.Database.Table.Relationships.entity.LabTest;
import com.example.Database.Table.Relationships.repository.LabTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LabTestService {
    private final LabTestRepository labTestRepository;

    public LabTest uploadNewLabTest(LabTest labtest){
        try {
            return labTestRepository.save(labtest);
        }catch (Exception e){
            return null;
        }
    }
    public LabTest getLabTestByName(String name){
        return labTestRepository.findByTestName(name).orElse(null);
    }
}
