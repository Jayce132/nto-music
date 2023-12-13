package com.musicshop.repository;

import com.musicshop.model.product.Product;
import com.musicshop.repository.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {

    private ProductRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new ProductRepository();
    }

    @Test
    public void testAddAndRemoveProduct() {
        Product newProduct = new Product();
        newProduct.setName("Test Product");
        newProduct.setDescription("Test Description");
        newProduct.setPrice(new BigDecimal("100.00"));
        newProduct.setQuantityAvailable(5);
        newProduct.setCategoryId(1L);

        Product savedProduct = repository.save(newProduct);

        assertNotNull(savedProduct.getId());
        assertEquals(newProduct.getName(), savedProduct.getName());

        repository.deleteById(savedProduct.getId());

        Optional<Product> deletedProduct = repository.findById(savedProduct.getId());
        assertTrue(deletedProduct.isEmpty());
    }

    @Test
    public void testUpdateProduct() {
        // Assume there's an existing product with ID 1
        Optional<Product> existingProductOpt = repository.findById(1L);
        assertTrue(existingProductOpt.isPresent());

        Product existingProduct = existingProductOpt.get();
        existingProduct.setName("Updated Name");
        existingProduct.setDescription("Updated Description");
        existingProduct.setPrice(new BigDecimal("200.00"));
        existingProduct.setQuantityAvailable(10);

        Product updatedProduct = repository.save(existingProduct);

        assertEquals("Updated Name", updatedProduct.getName());
        assertEquals("Updated Description", updatedProduct.getDescription());
        assertEquals(new BigDecimal("200.00"), updatedProduct.getPrice());
        assertEquals(10, updatedProduct.getQuantityAvailable());
    }
}
