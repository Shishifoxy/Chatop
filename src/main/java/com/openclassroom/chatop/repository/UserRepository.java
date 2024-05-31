package com.openclassroom.chatop.repository;

import com.openclassroom.chatop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}