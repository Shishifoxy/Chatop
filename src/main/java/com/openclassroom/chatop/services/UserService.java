package com.openclassroom.chatop.services;

import com.openclassroom.chatop.entity.User;
import com.openclassroom.chatop.mappers.UserMapper;
import com.openclassroom.chatop.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;
    public void getUserById(Long id) {
        this.userRepository.findById(id);
    }

}
