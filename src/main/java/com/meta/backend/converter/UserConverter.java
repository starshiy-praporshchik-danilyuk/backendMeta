package com.meta.backend.converter;

import com.meta.backend.dto.UserDto;
import com.meta.backend.entity.User;

public class UserConverter {

    public static UserDto toDto(User user){
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public static User toEntity(UserDto userDto){
        return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .role("USER")
                .build();
    }
}
