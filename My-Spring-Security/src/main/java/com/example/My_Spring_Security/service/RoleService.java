package com.example.My_Spring_Security.service;

import com.example.My_Spring_Security.entity.Role;
import com.example.My_Spring_Security.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role addRole(Role role) {
        try {
            return roleRepository.save(role);
        }catch (Exception e) {
            return null;
        }
    }
    public Role findRoleByName(String name) {
        System.out.println("Role name = "+name);
        return roleRepository.findByName(name).orElse(null);
    }
}
