package com.example.work_today.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;

@Data
public class DiseaseDto {
    @Schema(hidden = true)
    private Long id;
    private String disease_name;
    private ArrayList<QuestionsDto> questions;
}
