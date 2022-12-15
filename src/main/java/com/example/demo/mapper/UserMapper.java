package com.example.demo.mapper;

import com.example.demo.model.User;
import com.example.demo.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mapping(target = "username", source = "name")
    @Mapping(target = "sex", source = "gender")
    UserDto userToUserDto(User user);
}
