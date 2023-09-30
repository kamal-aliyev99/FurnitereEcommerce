package com.myangels.furnitereecommerce.repository;

import com.myangels.furnitereecommerce.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    @Query(nativeQuery = true, value = "SELECT cp.*\n" +
            "FROM cart_product cp\n" +
            "INNER JOIN cart c ON cp.cart_id = c.id\n" +
            "WHERE c.user_id = :userId\n" +
            "  AND cp.product_id = :productId")
    Optional<CartProduct> getProductByUserIdAndProductId(@Param("userId") Long userId,
                                                      @Param("productId") Long productId);
}
