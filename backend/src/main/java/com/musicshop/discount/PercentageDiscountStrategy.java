package com.musicshop.discount;

import com.musicshop.model.product.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentageDiscountStrategy implements DiscountStrategy {
    private final BigDecimal percent;

    public PercentageDiscountStrategy(double percent) {
        this.percent = BigDecimal.valueOf(percent).divide(BigDecimal.valueOf(100));
    }

    @Override
    public BigDecimal applyDiscount(Product product) {
        BigDecimal price = product.getPrice();

        // Apply discount to the entire price
        BigDecimal discountedPrice = price.multiply(BigDecimal.ONE.subtract(percent));

        // Round down to the nearest dollar and then add .99
        BigDecimal finalPrice = discountedPrice.setScale(0, RoundingMode.DOWN).add(new BigDecimal("0.99"));

        return finalPrice;
    }
}