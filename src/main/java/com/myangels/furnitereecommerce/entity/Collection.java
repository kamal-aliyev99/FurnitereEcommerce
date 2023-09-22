package com.myangels.furnitereecommerce.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "collection")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "collection_name")
    String collectionName;

    @Column(name = "photo")
    String photo;

}
