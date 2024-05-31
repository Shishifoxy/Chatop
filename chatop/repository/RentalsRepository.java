package com.openclassroom.chatop.repository;

import com.openclassroom.chatop.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalsRepository extends JpaRepository<Rental, Long> {
}