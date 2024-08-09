package com.openclassroom.chatop.controllers;

import com.openclassroom.chatop.dto.MessageDto;
import com.openclassroom.chatop.dto.UserDto;
import com.openclassroom.chatop.entity.Message;
import com.openclassroom.chatop.services.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {
@Autowired
private MessageService messageService;
    @GetMapping
    public ResponseEntity<List<MessageDto>> getMessages() {
        List<MessageDto> messages = messageService.getMessages();
        return ResponseEntity.ok(messages);
    }

    @PostMapping(path ="/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <MessageDto> addMessage(@RequestBody MessageDto message,  HttpServletRequest request) {
        this.messageService.saveMessage(message);
        return ResponseEntity.ok(message);
        //set user
    }


    @DeleteMapping("/{id}")
    public ResponseEntity <MessageDto> deleteMessage(@PathVariable Long id) {
        this.messageService.deleteMessage(id);
        return ResponseEntity.ok().build();
        // delete by user
    }

    @PutMapping(path ="/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <MessageDto> updateMessage(@RequestBody MessageDto message) {
        this.messageService.updateMessage(message);
        return ResponseEntity.ok(message);
        // by user
    }

    @GetMapping("/{id}")
    public ResponseEntity <MessageDto> getMessageById(@PathVariable Long id) {
        this.messageService.getMessageById(id);
        return ResponseEntity.ok().build();
    }

}
