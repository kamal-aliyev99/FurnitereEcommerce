package com.myangels.furnitereecommerce.mapper;

import com.myangels.furnitereecommerce.entity.Category;
import com.myangels.furnitereecommerce.entity.Collection;
import com.myangels.furnitereecommerce.error.ErrorCodes;
import com.myangels.furnitereecommerce.exception.NotFoundException;
import com.myangels.furnitereecommerce.model.dto.request.ProductRequest;
import com.myangels.furnitereecommerce.model.dto.response.ProductResponse;
import com.myangels.furnitereecommerce.entity.Product;
import com.myangels.furnitereecommerce.repository.CategoryRepository;
import com.myangels.furnitereecommerce.repository.CollectionRepository;

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

    public static Product toProduct(ProductRequest productRequest,
                                    CategoryRepository categoryRepository,
                                    CollectionRepository collectionRepository) {
        Category category = categoryRepository
                .findById(productRequest.getCategoryId())
                .orElseThrow(()-> NotFoundException.of(
                        String.format("Category not found %s", productRequest.getCategoryId()),
                        ErrorCodes.NOT_FOUND));

        Collection collection = collectionRepository
                .findById(productRequest.getCollectionId())
                .orElseThrow(()-> NotFoundException.of(
                        String.format("Collection not found %s", productRequest.getCollectionId()),
                        ErrorCodes.NOT_FOUND));

        return Product.builder()
                .category(category)
                .collection(collection)
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
