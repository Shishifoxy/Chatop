package com.openclassroom.chatop.controllers;

import com.openclassroom.chatop.entity.Message;
import com.openclassroom.chatop.services.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {
@Autowired
private MessageService messageService;

    @PostMapping(path ="/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Message> addMessage(@RequestBody Message message,  HttpServletRequest request) {
        this.messageService.save(message);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/test")
    public String test() {
        return "Hello World!";
    }

}
