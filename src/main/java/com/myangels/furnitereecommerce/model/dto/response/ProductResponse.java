package com.myangels.furnitereecommerce.model.dto.response;

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
public class ProductResponse {

    Long id;

    String name;

    String description;

    BigDecimal price;

    Double popularity;

    Integer stock;

    String color;

    List<String> photoUrls;

    Category category;

    Collection collection;

}
