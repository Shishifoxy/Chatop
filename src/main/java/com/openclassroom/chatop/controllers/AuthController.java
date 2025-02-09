package com.openclassroom.chatop.controllers;

import com.openclassroom.chatop.dto.LoginDto;
import com.openclassroom.chatop.dto.UserDto;
import com.openclassroom.chatop.entity.User;
import com.openclassroom.chatop.mappers.UserMapper;
import com.openclassroom.chatop.services.interfaces.AuthService;
import com.openclassroom.chatop.services.JWTService;
import com.openclassroom.chatop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Endpoints for authentication")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto user) {
        this.authService.register(user);
        return ResponseEntity.ok(user);
    }


    @Operation(summary = "Login")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto login) {
        Optional<User> user = this.userService.getUserByEmail(login.getEmail());
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "User not found"));
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword())
            );

            String token = this.jwtService.generateToken(user.get().getEmail());
            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid email or password"));
        }
    }


    @Operation(summary = "Get current user")
    @GetMapping("/me")

    public UserDto me() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userMapper.toDto(userService.getUserByEmail(email).get());
    }
}
