package com.myangels.furnitereecommerce.model.dto.request;

import com.myangels.furnitereecommerce.entity.Category;
import com.myangels.furnitereecommerce.entity.Collection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    String name;
    String description;
    BigDecimal price;
    String color;
    Double popularity;
    Integer stock;
    Category category;
    Collection collection;
    List<String> photoUrls;

}
