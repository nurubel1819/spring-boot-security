package com.example.work_today.controller;
import com.example.work_today.dto.DiseaseDto;
import com.example.work_today.dto.OptionDto;
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
        disease.setDisease_name(dto.getDisease());

        Question question = new Question();
        question.setQuestion(dto.getQuestions().get(0).getQuestion());
        question.setField_input(dto.getQuestions().get(0).getField_input());
        question.setField_type(dto.getQuestions().get(0).getField_type());

        OptionSelect optionSelect = new OptionSelect();
        optionSelect.setOptionName(dto.getQuestions().get(0).getOptions().get(0).option);

        question.setOptions(List.of(optionSelect));
        disease.setQuestions(List.of(question));

        disease = uploadService.save_disease(disease);

        return ResponseEntity.ok(disease);
    }
    @PostMapping("/upload-disease-multiple data")
    public ResponseEntity<?> uploadDiseaseMultiple(@RequestBody DiseaseDto dto) {

        Disease disease = new Disease();
        disease.setDisease_name(dto.getDisease());

        List<Question> questions = new ArrayList<>();

        for (QuestionsDto qdto : dto.getQuestions()) {
            Question question = new Question();
            question.setQuestion(qdto.getQuestion());
            question.setField_input(qdto.getField_input());
            question.setField_type(qdto.getField_type());

            List<OptionSelect> options = new ArrayList<>();
            for (OptionDto optDto : qdto.getOptions()) {
                OptionSelect option = new OptionSelect();
                option.setOptionName(optDto.getOption());
                options.add(option);
            }

            question.setOptions(options);
            questions.add(question);
        }

        disease.setQuestions(questions);

        Disease savedDisease = uploadService.save_disease(disease);

        return ResponseEntity.ok(savedDisease);
    }
    @PostMapping("/upload-all-json-file")
    public ResponseEntity<?> uploadDiseaseAllJson(@RequestBody List<DiseaseDto> dtoAll) {
        List<Disease> allUploadDisease = new ArrayList<>();
        for(DiseaseDto dto : dtoAll){
            Disease disease = new Disease();
            disease.setDisease_name(dto.getDisease());

            List<Question> questions = new ArrayList<>();

            for (QuestionsDto qdto : dto.getQuestions()) {
                Question question = new Question();
                question.setQuestion(qdto.getQuestion());
                question.setField_input(qdto.getField_input());
                question.setField_type(qdto.getField_type());

                List<OptionSelect> options = new ArrayList<>();
                for (OptionDto optDto : qdto.getOptions()) {
                    OptionSelect option = new OptionSelect();
                    option.setOptionName(optDto.getOption());
                    options.add(option);
                }

                question.setOptions(options);
                questions.add(question);
            }

            disease.setQuestions(questions);

            Disease savedDisease = uploadService.save_disease(disease);
            allUploadDisease.add(disease);
        }
        return ResponseEntity.ok(allUploadDisease);
    }


    @GetMapping("/get-all")
    ResponseEntity<?> get_all() {
        List<Disease> allDisease = new ArrayList<>();
        allDisease = uploadService.getAllDisease();
        return ResponseEntity.ok(allDisease);
    }
}

