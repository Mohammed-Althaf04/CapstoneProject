package com.wip.smartparking.mapper;

import com.wip.smartparking.dto.request.UserRequestDTO;
import com.wip.smartparking.dto.response.UserResponseDTO;
import com.wip.smartparking.entity.User;

public class UserMapper {

    public static User toEntity(UserRequestDTO dto) {

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        return user;
    }

    public static UserResponseDTO toResponseDTO(User user) {

        UserResponseDTO dto = new UserResponseDTO();

        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole());

        return dto;
    }
}