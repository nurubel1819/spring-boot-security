package com.example.Database.Table.Relationships.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String labName;
    private String address;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "lab_map_lab_test",
            joinColumns = @JoinColumn(name = "lab_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id"))
    private Set<LabTest> labTests = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}
