package com.openclassroom.chatop.services;

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
    public RentalsDto saveRental(RentalPictureDto rentalPictureDto) throws Exception {
        final Rental rental = rentalsMapper.toEntity(rentalPictureDto);
        final String url = pictureService.uploadFile(
                rentalPictureDto.getOwnerId(),
                rentalPictureDto.getFile()
        );
        rental.setPicture(url);

        final Rental rentalSaved = this.rentalsRepository.save(rental);
        return rentalsMapper.toDto(rentalSaved);
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
