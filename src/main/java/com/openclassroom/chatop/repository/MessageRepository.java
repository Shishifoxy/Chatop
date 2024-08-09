package com.openclassroom.chatop.repository;

import com.openclassroom.chatop.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Override
    List<Message> findAll();
}
