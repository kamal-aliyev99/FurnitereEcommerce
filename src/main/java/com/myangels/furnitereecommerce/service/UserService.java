package com.myangels.furnitereecommerce.service;

import com.myangels.furnitereecommerce.entity.Users;
import com.myangels.furnitereecommerce.exception.NotFoundException;
import com.myangels.furnitereecommerce.model.dto.request.UpdateUser;
import com.myangels.furnitereecommerce.model.dto.request.UserDto;
import com.myangels.furnitereecommerce.model.dto.request.UserInfo;
import com.myangels.furnitereecommerce.model.dto.request.UserRequest;
import com.myangels.furnitereecommerce.repository.CartRepository;
import com.myangels.furnitereecommerce.repository.UsersRepository;
import com.myangels.furnitereecommerce.repository.WishlistRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.FetchType;

import static com.myangels.furnitereecommerce.error.ErrorCodes.NOT_FOUND;
import static com.myangels.furnitereecommerce.mapper.UserMapper.toUser;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UsersRepository usersRepository;
    CartService cartService;
    WishlistService wishlistService;

    public void addUser(UserRequest userRequest) {
        usersRepository.save(toUser(userRequest));
    }

    @Transactional
    public void removeUserById(Long id) {
        var entity = fetchUserIfExist(id);
        usersRepository.delete(entity);
    }

    public UserDto checkUser(String email, String password) {
        var user = usersRepository.findUserInfoByEmailAndPassword(email, password)
                .orElseThrow(() -> NotFoundException
                        .of(String.format("User not found for email: %s and password: %s", email, password), NOT_FOUND));

        var cartDtoList = cartService.getCartByUserId(user.getId());
        var wishlist = wishlistService.getProductIdsByUserId(user.getId());
        return UserDto.builder()
                .userId(user.getId())
                .fullName(user.getFullName())
                .password(user.getPassword())
                .email(user.getEmail())
                .wishlist(wishlist)
                .cart(cartDtoList)
                .build();
    }

    public void updateUser(Long userId, UpdateUser updateUser) {
        var user = fetchUserIfExist(userId);
        fillIntoUserIfNonNull(user, updateUser);
    }

    private Users fetchUserIfExist(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> NotFoundException.of(NOT_FOUND));
    }

    private void fillIntoUserIfNonNull(Users user, UpdateUser updateUser) {
        var email = updateUser.getEmail();
        var password = updateUser.getPassword();
        var fullName = updateUser.getFullName();

        if (email != null && !email.trim().isEmpty()) {
            user.setEmail(email);
        }
        if (password != null && !password.trim().isEmpty()) {
            user.setPassword(password);
        }
        if (fullName != null && !fullName.trim().isEmpty()) {
            user.setFullName(fullName);
        }
        usersRepository.save(user);
    }
}
