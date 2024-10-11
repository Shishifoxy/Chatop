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

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");

    public void register(UserDto userDto) {
        if (!EMAIL_PATTERN.matcher(userDto.getEmail()).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use");
        }

        if (!PASSWORD_PATTERN.matcher(userDto.getPassword()).matches()) {
            throw new IllegalArgumentException("Password must contain at least 8 characters, one uppercase letter, one lowercase letter, one digit, and one special character");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        this.userRepository.save(userMapper.toEntity(userDto));
    }
}
