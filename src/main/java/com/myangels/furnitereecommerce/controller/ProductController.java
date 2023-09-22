package com.myangels.furnitereecommerce.controller;

import com.myangels.furnitereecommerce.model.dto.request.ProductRequest;
import com.myangels.furnitereecommerce.model.dto.response.ProductResponse;
import com.myangels.furnitereecommerce.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/v1/product")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductController {
    ProductService productService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.create(productRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ProductResponse getProduct(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<ProductResponse> getAllProduct() {
        return productService.getAllProducts();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void removeProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

}
