package com.myangels.furnitereecommerce.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartDto {
    Long productId;
    Integer pieces;
    String color;

}
