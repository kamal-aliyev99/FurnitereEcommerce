package com.myangels.furnitereecommerce.controller;

import com.myangels.furnitereecommerce.entity.Wishlist;
import com.myangels.furnitereecommerce.service.WishlistService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class WishlistController {

    WishlistService wishlistService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void create(@RequestParam("userId") Long userId,
                       @RequestParam("productId") Long productId) {
        log.info("Log.addProductToWishList.start {},{}", userId, productId);
        wishlistService.addProductToWishList(userId, productId);
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public void remove(@RequestParam("userId") Long userId,
                       @RequestParam("productId") Long productId) {
        log.info("Log.removeFromWishlist.start {},{}", userId, productId);
        wishlistService.removeFromWishlist(userId, productId);
    }

    @GetMapping("/user-wishlist/{userId}")
    @ResponseStatus(OK)
    public List<Long> getWishList(@PathVariable("userId") Long userId) {
        return wishlistService.getProductIdsByUserId(userId);
    }

}
