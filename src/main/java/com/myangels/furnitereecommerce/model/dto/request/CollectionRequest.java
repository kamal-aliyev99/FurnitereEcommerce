package com.myangels.furnitereecommerce.model.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollectionRequest {
    String collectionName;
    String photo;

}
