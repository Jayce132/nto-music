package com.musicshop.event.product;

import com.musicshop.model.product.Product;

import java.math.BigDecimal;

public class ProductDiscountEvent {

    private final Product discountedProduct;
    private final BigDecimal originalPrice;

    public ProductDiscountEvent(Product discountedProduct, BigDecimal originalPrice) {
        this.discountedProduct = discountedProduct;
        this.originalPrice = originalPrice;
    }

    // Getters
    public Product getDiscountedProduct() {
        return discountedProduct;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }
}

