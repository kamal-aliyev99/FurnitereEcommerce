package com.myangels.furnitereecommerce.repository;

import com.myangels.furnitereecommerce.entity.ProductPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPhotosRepository extends JpaRepository<ProductPhotos,Long> {
}
