package com.openclassroom.chatop.controllers;

import com.openclassroom.chatop.dto.RentalCreationDto;
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
    public ResponseEntity <Map<String, List<RentalsDto>>> getRentals() {
        Map<String, List<RentalsDto>> rentals = new HashMap<>();
        rentals.put("rentals", this.rentalService.getRentals());
        return ResponseEntity.ok(rentals);
    }

    @Operation(summary = "Get rental by id")
    @GetMapping("/{id}")
    public ResponseEntity <RentalsDto> getRentalById(@PathVariable Long id) {
        this.rentalService.getRentalById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Add a new rental")
    @PostMapping
    public ResponseEntity<RentalsDto> addRental(@ModelAttribute RentalCreationDto rentalCreationDto) throws Exception {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userService.getUserByEmail(email);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        rentalCreationDto.setOwnerId(user.get().getId());
        RentalsDto savedRental = rentalService.saveRental(rentalCreationDto);
        return ResponseEntity.ok(savedRental);
    }

    @PutMapping(path ="/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Map<String, String>> updateRental(@ModelAttribute RentalCreationDto rental) {
        this.rentalService.updateRental(rental);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Rental updated !");
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity <Map<String, String>> deleteRental(@PathVariable Long id) {
        this.rentalService.deleteRental(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Rental deleted !");
        return ResponseEntity.ok(response);
    }
}
