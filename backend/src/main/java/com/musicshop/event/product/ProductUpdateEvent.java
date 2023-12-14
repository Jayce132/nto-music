package com.musicshop.event.product;

import com.musicshop.model.product.Product;
import org.springframework.context.ApplicationEvent;

public class ProductUpdateEvent extends ApplicationEvent {
    private final Product updatedProduct;

    public ProductUpdateEvent(Object source, Product updatedProduct) {
        super(source);
        this.updatedProduct = updatedProduct;
    }

    public Product getUpdatedProduct() {
        return updatedProduct;
    }
}
