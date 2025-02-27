package com.openclassroom.chatop.controllers;

import com.openclassroom.chatop.dto.UserDto;
import com.openclassroom.chatop.entity.User;
import com.openclassroom.chatop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@Tag(name = "Users", description = "Endpoints for users")

public class UsersController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Get users by id")
    @GetMapping("/{id}")
    public ResponseEntity  <UserDto> getUserById(@PathVariable Long id) {
        this.userService.getUserById(id);
        return ResponseEntity.ok().build();
    }

}
