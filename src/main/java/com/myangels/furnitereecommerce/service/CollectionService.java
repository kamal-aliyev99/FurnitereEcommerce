package com.myangels.furnitereecommerce.service;

import com.myangels.furnitereecommerce.entity.Collection;
import com.myangels.furnitereecommerce.error.ErrorCodes;
import com.myangels.furnitereecommerce.exception.DuplicateNameException;
import com.myangels.furnitereecommerce.exception.NotFoundException;
import com.myangels.furnitereecommerce.mapper.CollectionMapper;
import com.myangels.furnitereecommerce.model.dto.request.CollectionRequest;
import com.myangels.furnitereecommerce.model.dto.response.CollectionResponse;
import com.myangels.furnitereecommerce.repository.CollectionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.myangels.furnitereecommerce.error.ErrorCodes.NOT_FOUND;
import static com.myangels.furnitereecommerce.mapper.CollectionMapper.toCollection;
import static com.myangels.furnitereecommerce.mapper.CollectionMapper.toCollectionResponse;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CollectionService {

    CollectionRepository collectionRepository;

    public CollectionResponse getCollectionByCollectionName(String collectionName) {
        var collection = collectionRepository
                .findCollectionByCollectionName(collectionName)
                .orElseThrow(() -> NotFoundException.of(NOT_FOUND));
        return toCollectionResponse(collection);
    }

    public List<CollectionResponse> getAllCollection() {
        return collectionRepository.findAll().stream()
                .map(CollectionMapper::toCollectionResponse)
                .collect(Collectors.toList());
    }

    public void addCollection(CollectionRequest collectionRequest) {
        var collection = collectionRepository
                .findCollectionByCollectionName(collectionRequest.getCollectionName());

        throwExIfCollectionExist(collection);
        collectionRepository.save(toCollection(collectionRequest));
    }

    public void deleteCollection(Long id) {
        var collection = fetchCollectionIfExist(id);
        collectionRepository.delete(collection);
    }

    private Collection fetchCollectionIfExist(Long id) {
        return collectionRepository.findById(id)
                .orElseThrow(() -> NotFoundException.of(NOT_FOUND));
    }

    private void throwExIfCollectionExist(Optional<Collection> collection) {
        if (collection.isPresent()) {
            throw DuplicateNameException.of(ErrorCodes.DUPLICATE_NAME);
        }
    }

}
