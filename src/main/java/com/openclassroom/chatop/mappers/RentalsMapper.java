package com.openclassroom.chatop.mappers;

import com.openclassroom.chatop.dto.RentalCreationDto;
import com.openclassroom.chatop.dto.RentalsDto;
import com.openclassroom.chatop.entity.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RentalsMapper {

    RentalsMapper INSTANCE = Mappers.getMapper(RentalsMapper.class);

    @Mapping(source = "owner.id", target = "ownerId")
    RentalsDto toDto(Rental rental);

    @Mapping(target = "picture", ignore = true)
    @Mapping(source = "ownerId", target = "owner.id")
    Rental toEntity(RentalCreationDto rentalCreationDto);
}