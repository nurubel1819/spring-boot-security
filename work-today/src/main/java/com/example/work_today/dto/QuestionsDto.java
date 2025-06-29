package com.example.work_today.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;

@Data
public class QuestionsDto {
    @Schema(hidden = true)
    private Long id;
    private String question;
    public String field_type;
    public String field_input;

    private ArrayList<OptionDto> options;
}
