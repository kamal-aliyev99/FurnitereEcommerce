package com.myangels.furnitereecommerce.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
    String categoryName;
    String photo;

}
