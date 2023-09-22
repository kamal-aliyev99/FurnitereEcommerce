package com.myangels.furnitereecommerce.mapper;

import com.myangels.furnitereecommerce.model.dto.request.ProductRequest;
import com.myangels.furnitereecommerce.model.dto.response.ProductResponse;
import com.myangels.furnitereecommerce.entity.Product;

public class ProductMapper {

    public static ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .category(product.getCategory())
                .price(product.getPrice())
                .collection(product.getCollection())
                .description(product.getDescription())
                .popularity(product.getPopularity())
                .stock(product.getStock())
                .photoUrls(product.getPhotoUrls())
                .name(product.getName())
                .color(product.getColor())
                .build();
    }

    public static Product toProduct(ProductRequest productRequest){
        return Product.builder()
                .category(productRequest.getCategory())
                .collection(productRequest.getCollection())
                .description(productRequest.getDescription())
                .stock(productRequest.getStock())
                .price(productRequest.getPrice())
                .name(productRequest.getName())
                .photoUrls(productRequest.getPhotoUrls())
                .popularity(productRequest.getPopularity())
                .color(productRequest.getColor())
                .build();
    }
}
