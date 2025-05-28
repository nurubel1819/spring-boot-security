package com.example.My_Spring_Security.service;

import com.example.My_Spring_Security.entity.MyUser;
import com.example.My_Spring_Security.entity.Role;
import com.example.My_Spring_Security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public MyUser saveUser(MyUser user) {
        if(userRepository.findByPhone(user.getPhone()).isPresent()){
            return null;
        }
        try {
            Role role = roleService.findRoleByName("USER");
            if(role==null){
                role = new Role();
                role.setName("USER");
                role = roleService.addRole(role);
            }
            user.getRoles().add(role);
            return userRepository.save(user);
        }catch (Exception e) {
            return null;
        }
    }
    public MyUser findUserByPhone(String phone) {
        return userRepository.findByPhone(phone).orElse(null);
    }
    public MyUser findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
    public MyUser setUserNewRole(MyUser user,Role role) {
        user.getRoles().add(role);
        return userRepository.save(user);
    }
}
