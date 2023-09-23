package com.myangels.furnitereecommerce.mapper;

import com.myangels.furnitereecommerce.entity.Category;
import com.myangels.furnitereecommerce.model.dto.request.CategoryRequest;
import com.myangels.furnitereecommerce.model.dto.response.CategoryResponse;

public class CategoryMapper {
    public static Category toCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .photo(categoryRequest.getPhoto())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .photo(category.getPhoto())
                .build();
    }

}
