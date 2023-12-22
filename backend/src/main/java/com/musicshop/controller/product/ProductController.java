package com.musicshop.controller.product;

import com.musicshop.dto.DetailedProductDTO;
import com.musicshop.dto.SimpleProductDTO;
import com.musicshop.factory.ProductDTOFactory;
import com.musicshop.model.product.Product;
import com.musicshop.event.product.ProductUpdateEvent;
import com.musicshop.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public ProductController(ProductRepository productRepository, ApplicationEventPublisher eventPublisher) {
        this.productRepository = productRepository;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping
    public List<SimpleProductDTO> listAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductDTOFactory::createSimpleProductDTO) // When I list the products I will use their simple representation
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedProductDTO> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> ResponseEntity.ok(ProductDTOFactory.createDetailedProductDTO(product))) // Here I use the more detailed DTO
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDetails.getName());
                    product.setDescription(productDetails.getDescription());
                    product.setPrice(productDetails.getPrice());
                    product.setQuantityAvailable(productDetails.getQuantityAvailable());
                    product.setCategory(productDetails.getCategory());

                    Product updatedProduct = productRepository.save(product);
                    eventPublisher.publishEvent(new ProductUpdateEvent(this, updatedProduct));

                    return ResponseEntity.ok(updatedProduct);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
