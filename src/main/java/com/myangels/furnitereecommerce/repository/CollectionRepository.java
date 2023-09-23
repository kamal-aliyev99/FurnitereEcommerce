package com.myangels.furnitereecommerce.repository;

import com.myangels.furnitereecommerce.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<Collection,Long> {
    Optional<Collection> findCollectionByCollectionName(String name);
}
