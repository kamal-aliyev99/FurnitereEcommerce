package com.myangels.furnitereecommerce.service;

import com.myangels.furnitereecommerce.entity.Category;
import com.myangels.furnitereecommerce.error.ErrorCodes;
import com.myangels.furnitereecommerce.exception.DuplicateNameException;
import com.myangels.furnitereecommerce.exception.NotFoundException;
import com.myangels.furnitereecommerce.mapper.CategoryMapper;
import com.myangels.furnitereecommerce.model.dto.request.CategoryRequest;
import com.myangels.furnitereecommerce.model.dto.response.CategoryResponse;
import com.myangels.furnitereecommerce.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.myangels.furnitereecommerce.error.ErrorCodes.NOT_FOUND;
import static com.myangels.furnitereecommerce.mapper.CategoryMapper.toCategory;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CategoryService {

    CategoryRepository categoryRepository;

    public List<CategoryResponse> getAllCategory() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper::toCategoryResponse)
                .collect(Collectors.toList());
    }

    public void addCategory(CategoryRequest categoryRequest) {
        var category = categoryRepository
                .findCategoryByCategoryName(categoryRequest.getCategoryName());

        throwExIfCategoryExist(category);
        categoryRepository.save(toCategory(categoryRequest));
    }

    public void deleteCategory(Long id) {
        var category = fetchCategoryIfExist(id);
        categoryRepository.delete(category);
    }

    private Category fetchCategoryIfExist(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> NotFoundException.of(NOT_FOUND));
    }

    private void throwExIfCategoryExist(Optional<Category> category) {
        if (category.isPresent()) {
            throw DuplicateNameException.of(ErrorCodes.DUPLICATE_NAME);
        }
    }

}
