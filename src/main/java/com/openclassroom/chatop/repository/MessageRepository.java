package com.openclassroom.chatop.repository;

import com.openclassroom.chatop.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
 public interface MessageRepository extends JpaRepository<Message, Long> {

    @Override
    List<Message> findAll();

}
