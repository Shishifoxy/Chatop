package com.openclassroom.chatop.services;

import com.openclassroom.chatop.dto.UserDto;
import com.openclassroom.chatop.mappers.UserMapper;
import com.openclassroom.chatop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");

    public void register(UserDto userDto) {
        if (!EMAIL_PATTERN.matcher(userDto.getEmail()).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("l'email est déjà utilisé");
        }

        if (!PASSWORD_PATTERN.matcher(userDto.getPassword()).matches()) {
            throw new IllegalArgumentException("le mot de passe doit contenir au moins 8 caractères, une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        this.userRepository.save(userMapper.toEntity(userDto));
    }
}
