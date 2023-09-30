package com.myangels.furnitereecommerce.controller;

import com.myangels.furnitereecommerce.model.dto.request.UpdateUser;
import com.myangels.furnitereecommerce.model.dto.request.UserRequest;
import com.myangels.furnitereecommerce.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {

    UserService userService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void create(@RequestBody UserRequest userRequest) {
        userService.addUser(userRequest);
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public void removeUser(@PathVariable Long id) {
        userService.removeUserById(id);
    }

    @PostMapping("update/{userId}")
    @ResponseStatus(NO_CONTENT)
    public void updateUser(@PathVariable("userId") Long userId,
                           @RequestBody UpdateUser updateUser) {
        userService.updateUser(userId, updateUser);
    }

}
