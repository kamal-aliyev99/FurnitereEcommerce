package com.myangels.furnitereecommerce.mapper;

import com.myangels.furnitereecommerce.entity.Users;
import com.myangels.furnitereecommerce.model.dto.request.UserRequest;


public class UserMapper {
    public static Users toUser(UserRequest userRequest) {
        return Users.builder()
                .email(userRequest.getEmail())
                .fullName(userRequest.getFullName())
                .password(userRequest.getPassword())
                .build();
    }

}
