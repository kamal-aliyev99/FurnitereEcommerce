package com.myangels.furnitereecommerce.controller;

import com.myangels.furnitereecommerce.model.dto.request.CategoryRequest;
import com.myangels.furnitereecommerce.model.dto.request.CollectionRequest;
import com.myangels.furnitereecommerce.model.dto.response.CategoryResponse;
import com.myangels.furnitereecommerce.model.dto.response.CollectionResponse;
import com.myangels.furnitereecommerce.service.CategoryService;
import com.myangels.furnitereecommerce.service.CollectionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

    CategoryService categoryService;

    @GetMapping
    @ResponseStatus(OK)
    public List<CategoryResponse> getAllCollection() {
        return categoryService.getAllCategory();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void create(@RequestBody CategoryRequest categoryRequest) {
        categoryService.addCategory(categoryRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void removeCollection(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

}
