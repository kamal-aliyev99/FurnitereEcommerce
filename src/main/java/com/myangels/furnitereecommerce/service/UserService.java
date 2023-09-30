package com.myangels.furnitereecommerce.service;

import com.myangels.furnitereecommerce.entity.Users;
import com.myangels.furnitereecommerce.exception.NotFoundException;
import com.myangels.furnitereecommerce.model.dto.request.UserRequest;
import com.myangels.furnitereecommerce.repository.UsersRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static com.myangels.furnitereecommerce.error.ErrorCodes.NOT_FOUND;
import static com.myangels.furnitereecommerce.mapper.UserMapper.toUser;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {

    UsersRepository usersRepository;

    public void addUser(UserRequest userRequest) {
        usersRepository.save(toUser(userRequest));
    }

    public void removeUserById(Long id) {
        var entity = fetchUserIfExist(id);
        usersRepository.delete(entity);
    }

    private Users fetchUserIfExist(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> NotFoundException.of(NOT_FOUND));
    }

}
