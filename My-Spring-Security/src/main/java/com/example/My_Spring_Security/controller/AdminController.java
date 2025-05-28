package com.example.My_Spring_Security.controller;

import com.example.My_Spring_Security.dto.RoleDto;
import com.example.My_Spring_Security.entity.MyUser;
import com.example.My_Spring_Security.entity.Role;
import com.example.My_Spring_Security.service.RoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final RoleService roleService;

    @GetMapping("/hello")
    private ResponseEntity<?> helloAdmin() {
        return ResponseEntity.ok("Hello i am Admin");
    }

    @PostMapping("/hi")
    private ResponseEntity<?> hiAdmin(@RequestBody String name) {
        return ResponseEntity.ok("Hi "+name);
    }

    @PostMapping("/add-new-role")
    public ResponseEntity<?> addNewRole(@RequestBody RoleDto dto) {
        Role role = new Role();
        role.setName(dto.getName());
        role = roleService.addRole(role);
        if(role != null) {return ResponseEntity.ok(role);}
        else {return ResponseEntity.badRequest().body(Map.of("message", "Role not added"));}
    }
    @PostMapping("/find-by-role-name")
    public ResponseEntity<?> findRoleByName(@RequestBody RoleDto dto) {
        Role role = roleService.findRoleByName(dto.getName());
        if(role != null) {
            Set<Long> userIds = role.getUsers().stream().map(MyUser::getId).collect(Collectors.toSet());
            dto.setUsers(userIds);
            return ResponseEntity.ok(dto);
        }
        else {return ResponseEntity.badRequest().body(Map.of("message", "Role not found"));}
    }
}
