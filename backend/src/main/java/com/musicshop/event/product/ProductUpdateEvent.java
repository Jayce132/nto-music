package com.musicshop.event.product;

import com.musicshop.model.product.Product;

public class ProductUpdateEvent {
    private final Product updatedProduct;

    public ProductUpdateEvent(Product updatedProduct) {
        this.updatedProduct = updatedProduct;
    }

    public Product getUpdatedProduct() {
        return updatedProduct;
    }
}
