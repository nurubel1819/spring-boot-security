package com.example.Database.Table.Relationships.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabTestDto {
    private Long id;
    private String testName;
    private String labName;
}
