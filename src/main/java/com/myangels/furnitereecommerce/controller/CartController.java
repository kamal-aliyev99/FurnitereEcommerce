package com.myangels.furnitereecommerce.controller;

import com.myangels.furnitereecommerce.entity.Cart;
import com.myangels.furnitereecommerce.model.dto.request.CartDto;
import com.myangels.furnitereecommerce.model.dto.request.CartRequest;
import com.myangels.furnitereecommerce.service.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("cart")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartController {

    CartService cartService;

    @PostMapping("/add-product")
    @ResponseStatus(CREATED)
    public void addProductToCart(@RequestBody CartRequest request) {
        cartService.addProductToCart(request);
    }

    @GetMapping("/{userId}")
    public List<CartDto> getCart(@PathVariable("userId") Long userId) {
        return cartService.getCartByUserId(userId);
    }

}
