package com.openclassroom.chatop.mappers;

import com.openclassroom.chatop.dto.RentalsDto;
import com.openclassroom.chatop.entity.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RentalsMapper {

    @Mapping(source = "owner.id", target = "ownerId")
    RentalsDto toDto(Rental rental);

    @Mapping(source = "ownerId", target = "owner.id")
    Rental toEntity(RentalsDto rentalsDto);
}

