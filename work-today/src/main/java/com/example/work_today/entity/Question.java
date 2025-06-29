package com.example.work_today.entity;

import com.example.work_today.dto.OptionDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    public String field_type;
    public String field_input;

    @ManyToOne
    @JoinColumn(name = "disease_id")
    public Disease disease;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    public List<OptionSelect> options = new ArrayList<>();
}
