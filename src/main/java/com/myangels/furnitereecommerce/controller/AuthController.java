package com.myangels.furnitereecommerce.controller;

import com.myangels.furnitereecommerce.entity.Users;
import com.myangels.furnitereecommerce.model.dto.request.UserDto;
import com.myangels.furnitereecommerce.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthController {

    UserService userService;

    @PostMapping("login")
    @ResponseStatus(OK)
    public UserDto login(@RequestParam("email") String email,
                         @RequestParam("password") String password) {
        return userService.checkUser(email, password);
    }
}
