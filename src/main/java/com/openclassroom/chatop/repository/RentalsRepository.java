package com.openclassroom.chatop.repository;

import com.openclassroom.chatop.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalsRepository extends JpaRepository<Rental, Long> {
    Optional<Rental> findById(Long id);

}