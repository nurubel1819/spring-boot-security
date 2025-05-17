package com.example.Database.Table.Relationships.controller;

import com.example.Database.Table.Relationships.dto.LabDto;
import com.example.Database.Table.Relationships.entity.Lab;
import com.example.Database.Table.Relationships.service.LabService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/labs")
@RequiredArgsConstructor
public class LabController {
    private final LabService labService;

    @PostMapping("/upload")
    ResponseEntity<?> uploadNewLab(@RequestBody LabDto labDto)
    {
        Lab lab = new Lab();
        lab.setLabName(labDto.getLabName());
        lab.setAddress(labDto.getAddress());
        Lab savedLab = labService.uploadNewLab(lab);
        if(savedLab!=null)
            return ResponseEntity.ok(savedLab);
        else
            return ResponseEntity.badRequest().body(Map.of("message","Failed to save lab"));
    }
}
