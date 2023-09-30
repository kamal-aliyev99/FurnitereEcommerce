package com.myangels.furnitereecommerce.repository;

import com.myangels.furnitereecommerce.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
    Optional<Wishlist> findByUser_Id(Long userId);
    @Query(value = "SELECT DISTINCT p.id FROM wishlist_product wp " +
            "LEFT JOIN product p ON wp.product_id = p.id " +
            "WHERE wp.wishlist_id = (SELECT id FROM wishlist WHERE user_id = :userId)",
            nativeQuery = true)
    List<Long> findProductIdsByUserId(@Param("userId") Long userId);
}
