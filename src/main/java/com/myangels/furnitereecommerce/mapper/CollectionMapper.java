package com.myangels.furnitereecommerce.mapper;

import com.myangels.furnitereecommerce.entity.Collection;
import com.myangels.furnitereecommerce.model.dto.request.CollectionRequest;
import com.myangels.furnitereecommerce.model.dto.response.CollectionResponse;

public class CollectionMapper {
    public static CollectionResponse toCollectionResponse(Collection collection) {
        return CollectionResponse.builder()
                .id(collection.getId())
                .collectionName(collection.getCollectionName())
                .photo(collection.getPhoto())
                .build();
    }

    public static Collection toCollection(CollectionRequest collectionRequest){
        return Collection.builder()
                .collectionName(collectionRequest.getCollectionName())
                .photo(collectionRequest.getPhoto())
                .build();
    }
}
