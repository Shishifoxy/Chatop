package com.openclassroom.chatop.services;

import com.openclassroom.chatop.dto.RentalsDto;
import com.openclassroom.chatop.entity.Rental;
import com.openclassroom.chatop.mappers.RentalsMapper;
import com.openclassroom.chatop.repository.RentalsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class RentalsService {

@Autowired
private RentalsRepository rentalsRepository;

@Autowired
private RentalsMapper rentalsMapper;

    public RentalsDto saveRental(RentalsDto rental) {
        return rentalsMapper.toDto(this.rentalsRepository.save(rentalsMapper.toEntity(rental)));
    }

    public List<RentalsDto> getRentals() {
        List<Rental> rentals = this.rentalsRepository.findAll();
        return rentals.stream()
                .map(rentalsMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteRental(Long id) {
        this.rentalsRepository.deleteById(id);
    }

    public void updateRental(RentalsDto rental) {
        this.rentalsRepository.save(rentalsMapper.toEntity(rental));
    }

    public void getRentalById(Long id) {
        this.rentalsRepository.findById(id);
    }
}
