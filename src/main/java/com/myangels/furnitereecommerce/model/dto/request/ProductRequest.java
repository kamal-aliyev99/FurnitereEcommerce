package com.myangels.furnitereecommerce.model.dto.request;

import com.myangels.furnitereecommerce.entity.Category;
import com.myangels.furnitereecommerce.entity.Collection;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    String name;
    String description;
    BigDecimal price;
    String color;
    Double popularity;
    Integer stock;
    Long categoryId;
    Long collectionId;
    List<String> photoUrls;

}
