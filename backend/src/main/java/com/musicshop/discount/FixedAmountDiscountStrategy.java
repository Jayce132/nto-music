package com.musicshop.discount;

import com.musicshop.model.product.Product;

import java.math.BigDecimal;

public class FixedAmountDiscountStrategy implements DiscountStrategy {
    private final BigDecimal discountAmount;

    public FixedAmountDiscountStrategy(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public BigDecimal applyDiscount(Product product) {
        return product.getPrice().subtract(discountAmount).max(BigDecimal.ZERO);
    }
}