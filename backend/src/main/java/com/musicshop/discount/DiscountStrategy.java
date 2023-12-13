package com.musicshop.discount;

import com.musicshop.model.product.Product;

import java.math.BigDecimal;

public interface DiscountStrategy {
    BigDecimal applyDiscount(Product product);
}
