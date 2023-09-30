package com.myangels.furnitereecommerce.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE )
public class CartRequest {
    Long userId;
    Long productId;
    Integer pieces;
    String color;

}
