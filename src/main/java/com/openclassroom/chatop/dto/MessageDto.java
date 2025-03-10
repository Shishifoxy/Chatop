package com.openclassroom.chatop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class MessageDto {

    private Long id;
    @NotNull(message = "Rental ID is required.")
    @JsonProperty("rental_id")
    private Long rentalId;
    @JsonProperty("user_id")
    @NotNull(message = "Rental ID is required.")
    private Long userId;
    @NotEmpty(message = "Message content cannot be empty.")
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MessageDto() {}

    public MessageDto(Long id, Long rentalId, Long userId, String message, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.rentalId = rentalId;
        this.userId = userId;
        this.message = message;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
