package com.openclassroom.chatop.controllers;

import com.openclassroom.chatop.dto.RentalPictureDto;
import com.openclassroom.chatop.dto.RentalsDto;
import com.openclassroom.chatop.entity.Rental;
import com.openclassroom.chatop.entity.User;
import com.openclassroom.chatop.repository.UserRepository;
import com.openclassroom.chatop.services.RentalsService;
import com.openclassroom.chatop.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/rentals")
@Tag(name = "Rentals", description = "Endpoints for rentals")

public class RentalsController {
@Autowired
private RentalsService rentalService;
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity <List<RentalsDto>> getRentals() {
        List<RentalsDto> rental = this.rentalService.getRentals();
        return ResponseEntity.ok(rental);
    }

    @Operation(summary = "Get rental by id")
    @GetMapping("/{id}")
    public ResponseEntity <RentalsDto> getRentalById(@PathVariable Long id) {
        this.rentalService.getRentalById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Add a new rental")
    @PostMapping
    public ResponseEntity <RentalsDto> addRental(@ModelAttribute RentalPictureDto rental) throws Exception {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = this.userService.getUserByEmail(email);
        if (user.isPresent()) {
            rental.setOwnerId(user.get().getId());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        RentalsDto savedRental = this.rentalService.saveRental(rental);
        return ResponseEntity.ok(savedRental);
    }

    @PutMapping(path ="/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Map<String, String>> updateRental(@ModelAttribute RentalsDto rental) {
        this.rentalService.updateRental(rental);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Rental updated !");
        return ResponseEntity.ok(response);
    }
}
