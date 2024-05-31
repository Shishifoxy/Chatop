package com.openclassroom.chatop.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {

    @GetMapping("/")
    public String getAll() {
        return "Get all rentals";
    }

    @GetMapping("/{id}")
    public String getRentalById() {
        return "Get rental";
    }
     @PostMapping("/")
    public String addRental() {
         return "Add rental";
     }

     @PutMapping("/{id}")
    public String updateRental() {
         return "Update rental";
     }
}
