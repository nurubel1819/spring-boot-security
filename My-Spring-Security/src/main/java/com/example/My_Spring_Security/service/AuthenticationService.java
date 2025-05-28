package com.example.My_Spring_Security.service;

import com.example.My_Spring_Security.dto.JwtAuthenticationResponseDto;
import com.example.My_Spring_Security.dto.SignInRequestDto;
import com.example.My_Spring_Security.entity.MyUser;
import com.example.My_Spring_Security.entity.Role;
import com.example.My_Spring_Security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    //private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public MyUser sinUp(MyUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        MyUser saveUser = userService.saveUser(user);
        if(saveUser.getId()==1L) // here make first user is admin
        {
            Role role = roleService.findRoleByName("ADMIN");
            if(role==null)
            {
                role = new Role();
                role.setName("ADMIN");
                role = roleService.addRole(role);
                saveUser = userService.setUserNewRole(saveUser,role);
            }
            else saveUser = userService.setUserNewRole(saveUser,role);
        }
        return saveUser;
    }

    public JwtAuthenticationResponseDto signIn(SignInRequestDto signInRequestDto) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInRequestDto.getPhone(),signInRequestDto.getPassword()
                    ));

            var user = userService.findUserByPhone(signInRequestDto.getPhone());//.orElseThrow(()-> new IllegalArgumentException("Invalid username or password"));
            var token = jwtUtils.generateToken(user.getId(), user.getPhone(), List.of(user.getRoles().stream().map(Role::getName).toArray(String[]::new)));
            //var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);

            System.out.println("Token: "+token);
            JwtAuthenticationResponseDto jwtAuthenticationResponseDto = new JwtAuthenticationResponseDto();
            jwtAuthenticationResponseDto.setToken(token);
            return jwtAuthenticationResponseDto;
        }catch (Exception e){
            return null;
        }
    }

}