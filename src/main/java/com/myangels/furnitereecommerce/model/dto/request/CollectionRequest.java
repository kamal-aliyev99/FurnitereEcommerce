package com.myangels.furnitereecommerce.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CollectionRequest {
    String collectionName;
    String photo;

}
