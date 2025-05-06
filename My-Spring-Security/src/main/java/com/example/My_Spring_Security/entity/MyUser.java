package com.example.My_Spring_Security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "my_user")
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles", // মাঝখানের টেবিলের নাম
            joinColumns = @JoinColumn(name = "user_id"), // বর্তমান entity এর কলাম
            inverseJoinColumns = @JoinColumn(name = "role_id") // অপর entity এর কলাম
    )
    private Set<Role> roles = new HashSet<>();
}
