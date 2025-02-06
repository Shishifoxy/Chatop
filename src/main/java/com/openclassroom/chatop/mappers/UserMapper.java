package com.openclassroom.chatop.mappers;

import com.openclassroom.chatop.dto.UserDto;
import com.openclassroom.chatop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "name", target = "name")  // Mapping correct entre "name" et "name"
    UserDto toDto(User user);

    @Mapping(source = "name", target = "name")  // Mapping correct entre "name" et "name"
    User toEntity(UserDto userDto);
}