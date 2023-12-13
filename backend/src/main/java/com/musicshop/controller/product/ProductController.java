package com.musicshop.controller.product;

import com.musicshop.event.product.ProductUpdateEvent;
import com.musicshop.event.product.ProductUpdateListener;
import com.musicshop.model.product.Product;
import com.musicshop.repository.product.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductController {

    private final ProductRepository productRepository;

    private final List<ProductUpdateListener> listeners = new ArrayList<>();

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
        notifyProductUpdateListeners(new ProductUpdateEvent(product));
    }

    public void registerProductUpdateListener(ProductUpdateListener listener) {
        listeners.add(listener);
    }

    private void notifyProductUpdateListeners(ProductUpdateEvent event) {
        for (ProductUpdateListener listener : listeners) {
            listener.onProductUpdate(event);
        }
    }
}
