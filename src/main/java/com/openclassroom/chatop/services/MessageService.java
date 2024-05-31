package com.openclassroom.chatop.services;

import com.openclassroom.chatop.entity.Message;
import com.openclassroom.chatop.repository.MessageRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class MessageService {
@Autowired
private MessageRepository messageRepository;

    public void save(Message message) {
        this.messageRepository.save(message);
    }
}
