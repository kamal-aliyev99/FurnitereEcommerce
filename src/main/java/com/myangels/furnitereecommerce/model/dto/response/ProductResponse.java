package com.myangels.furnitereecommerce.model.dto.response;

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

    String category;

    String collection;

    Double popularity;

    Integer stock;

    String color;

    List<String> photoUrls;

}
