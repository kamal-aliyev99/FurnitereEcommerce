package com.myangels.furnitereecommerce.service;

import com.myangels.furnitereecommerce.entity.Wishlist;
import com.myangels.furnitereecommerce.exception.NotFoundException;
import com.myangels.furnitereecommerce.repository.ProductRepository;
import com.myangels.furnitereecommerce.repository.UsersRepository;
import com.myangels.furnitereecommerce.repository.WishlistRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.myangels.furnitereecommerce.error.ErrorCodes.NOT_FOUND;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class WishlistService {

    UsersRepository usersRepository;
    ProductRepository productRepository;
    WishlistRepository wishlistRepository;
    static Wishlist EMPTY_WISHLIST = new Wishlist();


    public void addProductToWishList(Long userId, Long productId) {
        var user = usersRepository
                .findById(userId)
                .orElseThrow(() -> NotFoundException.of(NOT_FOUND));

        var product = productRepository
                .findProductById(productId)
                .orElseThrow(() -> NotFoundException.of(NOT_FOUND));

        var wishlist = user.getWishlist();
        if (wishlist == null) {
            wishlist = new Wishlist();
            wishlist.setUser(user);
        }

        wishlist.addProduct(product);
        wishlistRepository.save(wishlist);
    }

    public void removeFromWishlist(Long userId, Long productId) {
        var user = usersRepository
                .findById(userId)
                .orElseThrow(() -> NotFoundException.of(NOT_FOUND));

        var product = productRepository
                .findProductById(productId)
                .orElseThrow(() -> NotFoundException.of(NOT_FOUND));

        var wishlist = user.getWishlist();

        if (wishlist != null) {
            wishlist.removeProduct(product);
            wishlistRepository.save(wishlist);
        }

    }

    public List<Long> getProductIdsByUserId(Long userId) {
        return wishlistRepository.findProductIdsByUserId(userId);
    }

}
