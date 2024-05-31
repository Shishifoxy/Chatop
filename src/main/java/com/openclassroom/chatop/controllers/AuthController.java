package com.openclassroom.chatop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public String register() {
        return "Register user";
    }

    @PostMapping("/login")
    public String login() {
        return "Login user";
    }

    @GetMapping("/me")
    public String me() {
        return "Get user";
    }
}
