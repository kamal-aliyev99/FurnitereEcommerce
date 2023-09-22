package com.myangels.furnitereecommerce.service;

import com.myangels.furnitereecommerce.model.dto.request.ProductRequest;
import com.myangels.furnitereecommerce.model.dto.response.ProductResponse;
import com.myangels.furnitereecommerce.entity.Product;
import com.myangels.furnitereecommerce.exception.NotFoundException;
import com.myangels.furnitereecommerce.mapper.ProductMapper;
import com.myangels.furnitereecommerce.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.myangels.furnitereecommerce.error.ErrorCodes.NOT_FOUND;
import static com.myangels.furnitereecommerce.mapper.ProductMapper.toProduct;
import static com.myangels.furnitereecommerce.mapper.ProductMapper.toProductResponse;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;
    ProductPhotosService productPhotosService;

    @Transactional
    public void create(ProductRequest productRequest) {
        productRepository.save(toProduct(productRequest));
    }

    public ProductResponse findProductById(Long id) {
        var product = fetchProductIfExist(id);
        return toProductResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public void deleteProductById(Long id) {
        var entity = fetchProductIfExist(id);
        productRepository.delete(entity);
    }

    private Product fetchProductIfExist(Long id) {
        return productRepository
                .findProductById(id)
                .orElseThrow(() -> NotFoundException.of(NOT_FOUND));
    }

}
