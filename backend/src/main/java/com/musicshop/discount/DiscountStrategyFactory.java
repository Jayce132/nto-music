package com.musicshop.discount;

import java.math.BigDecimal;

public class DiscountStrategyFactory {
    public static DiscountStrategy createDiscountStrategy(DiscountType type, double value) {
        switch (type) {
            case PERCENTAGE:
                return new PercentageDiscountStrategy(value);
            case FIXED:
                return new FixedAmountDiscountStrategy(new BigDecimal(value));
            default:
                throw new IllegalArgumentException("Unknown discount type: " + type);
        }
    }
}


