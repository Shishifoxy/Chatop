package com.openclassroom.chatop.services;

import com.openclassroom.chatop.dto.MessageDto;
import com.openclassroom.chatop.entity.Message;
import com.openclassroom.chatop.mappers.MessageMapper;
import com.openclassroom.chatop.repository.MessageRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageMapper messageMapper;


    public void saveMessage(MessageDto message) {
        this.messageRepository.save(messageMapper.toEntity(message));
    }

}
