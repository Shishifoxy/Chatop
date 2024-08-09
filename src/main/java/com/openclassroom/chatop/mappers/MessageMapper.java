package com.openclassroom.chatop.mappers;

import com.openclassroom.chatop.dto.MessageDto;
import com.openclassroom.chatop.entity.Message;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MessageMapper {

    @Mapping(source = "rental.id", target = "rentalId")
    @Mapping(source = "user.id", target = "userId")
    MessageDto toDto(Message message);

    @Mapping(source = "rentalId", target = "rental.id")
    @Mapping(source = "userId", target = "user.id")
    Message toEntity(MessageDto messageDto);
}
