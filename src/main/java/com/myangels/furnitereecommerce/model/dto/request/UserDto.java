package com.myangels.furnitereecommerce.model.dto.request;

import com.myangels.furnitereecommerce.entity.Cart;
import com.myangels.furnitereecommerce.entity.Wishlist;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    Long userId;
    String email;
    String fullName;
    String password;
    List<CartDto> cart;
    List<Long> wishlist;

}
