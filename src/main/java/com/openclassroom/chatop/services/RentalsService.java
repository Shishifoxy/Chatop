package com.openclassroom.chatop.services;

import com.openclassroom.chatop.dto.RentalCreationDto;
import com.openclassroom.chatop.dto.RentalPictureDto;
import com.openclassroom.chatop.dto.RentalsDto;
import com.openclassroom.chatop.entity.Rental;
import com.openclassroom.chatop.mappers.RentalsMapper;
import com.openclassroom.chatop.repository.RentalsRepository;
import jakarta.transaction.Transactional;
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

@Autowired
private UploadPictureService pictureService;

    private static String RENTAL_FOLDER = "rental";


    @Transactional
    public RentalsDto saveRental(RentalCreationDto rentalCreationDto) throws Exception {
        Rental rental = rentalsMapper.toEntity(rentalCreationDto);
        rental.getOwner().getId();
        String pictureUrl = pictureService.uploadFile(
                rentalCreationDto.getOwnerId(),
                rentalCreationDto.getPicture()
        );
        rental.setPicture(pictureUrl);
        Rental savedRental = rentalsRepository.save(rental);
        return rentalsMapper.toDto(savedRental);
    }
    public List<RentalsDto> getRentals() {
        List<Rental> rentals = rentalsRepository.findAll();
        return rentals.stream()
                .map(rentalsMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteRental(Long id) {
        this.rentalsRepository.deleteById(id);
    }

    public void updateRental(RentalCreationDto rental) {
        this.rentalsRepository.save(rentalsMapper.toEntity(rental));
    }

    public void getRentalById(Long id) {
        this.rentalsRepository.findById(id);
    }
}
