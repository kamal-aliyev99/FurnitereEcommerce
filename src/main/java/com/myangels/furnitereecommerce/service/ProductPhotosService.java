package com.myangels.furnitereecommerce.service;

import com.myangels.furnitereecommerce.entity.Product;
import com.myangels.furnitereecommerce.entity.ProductPhotos;
import com.myangels.furnitereecommerce.repository.ProductPhotosRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductPhotosService {
    ProductPhotosRepository productPhotosRepository;

    public void saveProductPhotos(Product product, List<String> photoUrls) {
        productPhotosRepository.save(buildProductPhoto(product, photoUrls));
    }

    private ProductPhotos buildProductPhoto(Product product, List<String> photoUrls) {
        return ProductPhotos.builder()
                .product(product)
                .build();
    }
}
