package com.openclassroom.chatop.services;

import com.openclassroom.chatop.dto.RentalCreationDto;
import com.openclassroom.chatop.dto.RentalsDto;
import com.openclassroom.chatop.entity.Rental;
import com.openclassroom.chatop.mappers.RentalsMapper;
import com.openclassroom.chatop.repository.RentalsRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
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

    @Transactional
    public RentalsDto updateRental(Long id, RentalCreationDto rentalCreationDto) throws Exception {
        Rental existingRental = rentalsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rental not found"));
        if (!existingRental.getOwner().getId().equals(rentalCreationDto.getOwnerId())) {
            throw new AccessDeniedException("You are not the owner of this rental");
        }
        existingRental.setName(rentalCreationDto.getName());
        existingRental.setSurface(rentalCreationDto.getSurface());
        existingRental.setPrice(rentalCreationDto.getPrice());
        existingRental.setDescription(rentalCreationDto.getDescription());
        if (rentalCreationDto.getPicture() != null) {
            String pictureUrl = pictureService.uploadFile(
                    rentalCreationDto.getOwnerId(),
                    rentalCreationDto.getPicture()
            );
            existingRental.setPicture(pictureUrl);
        }
        return rentalsMapper.toDto(rentalsRepository.save(existingRental));
    }

    public RentalsDto getRentalById(Long id) {
        Optional<Rental> rentalOptional = this.rentalsRepository.findById(id);

        if (rentalOptional.isPresent()) {
            Rental rental = rentalOptional.get();
            return rentalsMapper.toDto(rental);
        } else {
            throw new RuntimeException("Rental not found with id: " + id);
        }
    }
}
