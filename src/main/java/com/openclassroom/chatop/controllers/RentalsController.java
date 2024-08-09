package com.openclassroom.chatop.controllers;

import com.openclassroom.chatop.dto.RentalsDto;
import com.openclassroom.chatop.entity.Rental;
import com.openclassroom.chatop.services.RentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
@Autowired
private RentalsService rentalService;
    @GetMapping
    public ResponseEntity <List<RentalsDto>> getRentals() {
        List<RentalsDto> rental = this.rentalService.getRentals();
        return ResponseEntity.ok(rental);
    }

    @GetMapping("/{id}")
    public ResponseEntity <RentalsDto> getRentalById(@PathVariable Long id) {
        this.rentalService.getRentalById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity <RentalsDto> addRental(@ModelAttribute RentalsDto rental) {
        RentalsDto savedRental = this.rentalService.saveRental(rental);
        return ResponseEntity.ok(savedRental);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <RentalsDto> deleteRental(@PathVariable Long id) {
        this.rentalService.deleteRental(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path ="/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <RentalsDto> updateRental(@ModelAttribute RentalsDto rental) {
        this.rentalService.updateRental(rental);
        return ResponseEntity.ok(rental);
    }
}
