package com.openclassroom.chatop.controllers;

import com.openclassroom.chatop.dto.LoginDto;
import com.openclassroom.chatop.dto.UserDto;
import com.openclassroom.chatop.entity.User;
import com.openclassroom.chatop.mappers.UserMapper;
import com.openclassroom.chatop.repository.UserRepository;
import com.openclassroom.chatop.services.AuthService;
import com.openclassroom.chatop.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto user) {
        this.authService.register(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto login) {
        Optional<User> user = this.userRepository.findByEmail(login.getEmail());
        if (user.isEmpty()) {
            return "User not found";
        }
//        if (!user.get().getPassword().equals(this.passwordEncoder.encode(login.getPassword()))) {
//            return "Invalid password";
//        }
        String token = this.jwtService.generateToken(user.get().getEmail());
        return token;

    }

    @GetMapping("/me")
    public UserDto me() {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userMapper.toDto(userRepository.findByEmail(email).get());
    }
}
