package com.myangels.furnitereecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.*;

@Data
@Table(name = "wishlist")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToMany
    @JoinTable(
            name = "wishlist_product",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonIgnoreProperties("wishlists")
    private Set<Product> products = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "user_id") // Assuming user_id is the foreign key column
    private Users user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wishlist wishlist = (Wishlist) o;
        return id.equals(wishlist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
