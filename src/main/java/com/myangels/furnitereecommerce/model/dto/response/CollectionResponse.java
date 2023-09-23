package com.myangels.furnitereecommerce.model.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollectionResponse {
    Long id;
    String collectionName;
    String photo;

}
