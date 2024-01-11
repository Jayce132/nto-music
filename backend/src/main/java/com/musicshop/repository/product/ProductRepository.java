package com.musicshop.repository.product;

import com.musicshop.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String productName);

    void deleteByName(String productName);
}
