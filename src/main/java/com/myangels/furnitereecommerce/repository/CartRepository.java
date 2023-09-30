package com.myangels.furnitereecommerce.repository;

import com.myangels.furnitereecommerce.entity.Cart;
import com.myangels.furnitereecommerce.model.dto.CartView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(nativeQuery = true, value = "SELECT p.id AS productId, cp.color, cp.pieces\n" +
            "FROM cart_product cp\n" +
            "INNER JOIN cart c ON cp.cart_id = c.id\n" +
            "INNER JOIN product p ON cp.product_id = p.id\n" +
            "WHERE c.user_id = :userId")
    List<CartView> getCardByUserId(@Param("userId") Long userId);

    Cart findByUserId(Long userId);
}
