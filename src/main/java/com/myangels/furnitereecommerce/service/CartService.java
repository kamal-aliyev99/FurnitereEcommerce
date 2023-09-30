package com.myangels.furnitereecommerce.service;

import com.myangels.furnitereecommerce.entity.Cart;
import com.myangels.furnitereecommerce.entity.CartProduct;
import com.myangels.furnitereecommerce.entity.Product;
import com.myangels.furnitereecommerce.exception.NotFoundException;
import com.myangels.furnitereecommerce.model.dto.CartView;
import com.myangels.furnitereecommerce.model.dto.request.CartDto;
import com.myangels.furnitereecommerce.model.dto.request.CartRequest;
import com.myangels.furnitereecommerce.repository.CartProductRepository;
import com.myangels.furnitereecommerce.repository.CartRepository;
import com.myangels.furnitereecommerce.repository.ProductRepository;
import com.myangels.furnitereecommerce.repository.UsersRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.myangels.furnitereecommerce.error.ErrorCodes.NOT_FOUND;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CartService {
    CartRepository cartRepository;
    UsersRepository usersRepository;
    ProductRepository productRepository;
    CartProductRepository cartProductRepository;

    @Transactional
    public void addProductToCart(CartRequest request) {

        var user = usersRepository.findById(request.getUserId())
                .orElseThrow(() -> NotFoundException
                        .of(String.format("User not found %s", request.getUserId()), NOT_FOUND));

        var cart = cartRepository.findByUserId(request.getUserId());
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
            usersRepository.save(user);
            cartRepository.save(cart);
        }


        var product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> NotFoundException
                        .of(String.format("Product not found %s", request.getProductId()), NOT_FOUND));

        saveProduct(product, request, cart);
    }

    @Transactional
    public void removeProductFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw NotFoundException
                    .of(String.format("User not found %s", userId), NOT_FOUND);
        }

        var products = cart.getProducts();
        products.removeIf(product -> product.getId().equals(productId));
        cartRepository.save(cart);
    }

    private void saveProduct(Product product, CartRequest request, Cart cart) {
        var existingProductOnCard = cartProductRepository
                .getProductByUserIdAndProductId(request.getUserId(), request.getProductId());

        if (existingProductOnCard.isPresent()) {
            var cartProduct = existingProductOnCard.get();
            cartProduct.setColor(request.getColor());
            cartProduct.setPieces(request.getPieces());
            cartProductRepository.save(cartProduct);
        } else {
            // Create a new CartProduct and set its attributes
            var cartProduct = new CartProduct();
            cartProduct.setProduct(product);
            cartProduct.setColor(request.getColor());
            cartProduct.setPieces(request.getPieces());

            // Set the association between cart and cartProduct
            cartProduct.setCart(cart);

            // Save the cartProduct to the repository
            cartProductRepository.save(cartProduct);
        }
    }


    public List<CartDto> getCartByUserId(Long userId) {
        var cartViewList = cartRepository.getCardByUserId(userId);
        if (cartViewList.isEmpty()) {
            return Collections.emptyList();
        }
        return buildCartDtoList(cartViewList);
    }

    private List<CartDto> buildCartDtoList(List<CartView> cartViewList) {
        return cartViewList.stream()
                .map(view -> {
                    return CartDto.builder()
                            .productId(view.getProductId())
                            .color(view.getColor())
                            .pieces(view.getPieces())
                            .build();
                }).collect(Collectors.toList());
    }

}

