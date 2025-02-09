package com.openclassroom.chatop.controllers;

import com.openclassroom.chatop.dto.MessageDto;
import com.openclassroom.chatop.dto.UserDto;
import com.openclassroom.chatop.entity.Message;
import com.openclassroom.chatop.entity.User;
import com.openclassroom.chatop.repository.UserRepository;
import com.openclassroom.chatop.services.MessageService;
import com.openclassroom.chatop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
@Tag(name = "Messages", description = "Endpoints for messages")

public class MessagesController {
@Autowired
private MessageService messageService;
@Autowired
private UserService userService;


@Operation(summary = "Post a new message")
    @PostMapping
public ResponseEntity<Map<String, String>> addMessage(@Valid @RequestBody MessageDto message, HttpServletRequest request) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = this.userService.getUserByEmail(email);
        if (user.isPresent()) {
            message.setUserId(user.get().getId());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        this.messageService.saveMessage(message);
    Map<String, String> response = new HashMap<>();
    response.put("message", "Message send with success");
        return ResponseEntity.ok(response)   ;
    }


}
