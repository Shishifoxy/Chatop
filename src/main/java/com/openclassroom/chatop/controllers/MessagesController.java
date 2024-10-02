package com.openclassroom.chatop.controllers;

import com.openclassroom.chatop.dto.MessageDto;
import com.openclassroom.chatop.dto.UserDto;
import com.openclassroom.chatop.entity.Message;
import com.openclassroom.chatop.entity.User;
import com.openclassroom.chatop.repository.UserRepository;
import com.openclassroom.chatop.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {
@Autowired
private MessageService messageService;
@Autowired
private UserRepository userRepository;


@Operation(summary = "Get all messages")
    @PostMapping(path ="/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <MessageDto> addMessage(@RequestBody MessageDto message,  HttpServletRequest request) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isPresent()) {
            message.setUserId(user.get().getId());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        this.messageService.saveMessage(message);

        return ResponseEntity.ok(message);
    }


}
