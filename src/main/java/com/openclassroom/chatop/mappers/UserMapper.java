package com.openclassroom.chatop.mappers;


import com.openclassroom.chatop.dto.UserDto;
import com.openclassroom.chatop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "name", target = "username")
    UserDto toDto(User user);

    @Mapping(source = "username", target = "name")
    User toEntity(UserDto userDto);
}