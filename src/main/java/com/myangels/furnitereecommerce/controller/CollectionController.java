package com.myangels.furnitereecommerce.controller;

import com.myangels.furnitereecommerce.entity.Collection;
import com.myangels.furnitereecommerce.model.dto.request.CollectionRequest;
import com.myangels.furnitereecommerce.model.dto.response.CollectionResponse;
import com.myangels.furnitereecommerce.service.CollectionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/collection")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CollectionController {

    CollectionService collectionService;

    @GetMapping("/{collectionName}")
    public CollectionResponse getCollection(@PathVariable("collectionName") String collectionName) {
        return collectionService.getCollectionByCollectionName(collectionName);
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<CollectionResponse> getAllCollection() {
        return collectionService.getAllCollection();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void create(@RequestBody CollectionRequest collectionRequest) {
        collectionService.addCollection(collectionRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void removeCollection(@PathVariable Long id) {
        collectionService.deleteCollection(id);
    }


}
