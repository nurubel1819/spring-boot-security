package com.example.work_today.controller;

import com.example.work_today.dto.DiseaseDto;
import com.example.work_today.dto.QuestionsDto;
import com.example.work_today.entity.Disease;
import com.example.work_today.entity.OptionSelect;
import com.example.work_today.entity.Question;
import com.example.work_today.repository.DiseaseRepo;
import com.example.work_today.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/disease")
@RequiredArgsConstructor
public class UploadDataController {

    private final UploadService uploadService;
    private final DiseaseRepo diseaseRepo;

    @PostMapping("/upload-disease-details")
    ResponseEntity<?> upload_disease(@RequestBody DiseaseDto dto) {

        Disease disease = new Disease();
        disease.setDisease_name(dto.getDisease_name());

        Question question = new Question();
        question.setQuestion(dto.getQuestions().get(0).getQuestion());
        question.setField_input(dto.getQuestions().get(0).getField_input());
        question.setField_type(dto.getQuestions().get(0).getField_type());
        question.setDisease(disease);

        OptionSelect optionSelect = new OptionSelect();
        optionSelect.setOptionName(dto.getQuestions().get(0).getOptions().get(0).option);
        optionSelect.setQuestion(question);

        question.setOptions(List.of(optionSelect));
        disease.setQuestions(List.of(question));
        disease = uploadService.save_disease(disease);

        return ResponseEntity.ok(disease);

    }
    @GetMapping("/get-all")
    ResponseEntity<?> get_all() {
        List<Disease> allDisease = new ArrayList<>();
        allDisease = uploadService.getAllDisease();
        return ResponseEntity.ok(allDisease);
    }
}

