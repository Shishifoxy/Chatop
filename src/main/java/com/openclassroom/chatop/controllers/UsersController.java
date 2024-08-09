package com.openclassroom.chatop.controllers;

import com.openclassroom.chatop.dto.UserDto;
import com.openclassroom.chatop.entity.User;
import com.openclassroom.chatop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity  <UserDto> getUserById(Long id) {
        this.userService.getUserById(id);
        return ResponseEntity.ok().build();
    }

}
