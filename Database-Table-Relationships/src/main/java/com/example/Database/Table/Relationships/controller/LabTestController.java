package com.example.Database.Table.Relationships.controller;

import com.example.Database.Table.Relationships.dto.LabTestDto;
import com.example.Database.Table.Relationships.entity.Lab;
import com.example.Database.Table.Relationships.entity.LabTest;
import com.example.Database.Table.Relationships.service.LabService;
import com.example.Database.Table.Relationships.service.LabTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/lab-tests")
@RequiredArgsConstructor
public class LabTestController {
    private final LabTestService labTestService;
    private final LabService labService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadNewLabTest(@RequestBody LabTestDto labTestDto) {
        try {
            Lab lab = labService.getLabByName(labTestDto.getLabName());
            if (lab == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "Save failed, Lab not found"));
            }

            // Ensure labTests is mutable and initialized
            if (lab.getLabTests() == null) {
                lab.setLabTests(new HashSet<>());
            }

            LabTest test = new LabTest();  // Create new LabTest object
            test.setTestName(labTestDto.getTestName());
            test.setLabs(new HashSet<>(Set.of(lab)));  // Set Lab as the only Lab in the Set

            lab.getLabTests().add(test);

            labService.uploadNewLab(lab);

            return ResponseEntity.ok(Map.of("message", "Lab & Test saved successfully"));

        } catch (Exception e) {
            e.printStackTrace(); // Good for debugging in development
            return ResponseEntity.badRequest().body(Map.of("message", "Exception: " + e.getMessage()));
        }
    }



}
