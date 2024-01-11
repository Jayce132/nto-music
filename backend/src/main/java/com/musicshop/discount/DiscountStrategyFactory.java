package com.musicshop.discount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscountStrategyFactory {

    private final FixedAmountDiscountStrategy fixedAmountDiscountStrategy;
    private final PercentageDiscountStrategy percentageDiscountStrategy;

    @Autowired
    public DiscountStrategyFactory(FixedAmountDiscountStrategy fixedAmountDiscountStrategy,
                                   PercentageDiscountStrategy percentageDiscountStrategy) {
        this.fixedAmountDiscountStrategy = fixedAmountDiscountStrategy;
        this.percentageDiscountStrategy = percentageDiscountStrategy;
    }

    public DiscountStrategy getDiscountStrategy(String type) {
        if ("fixed".equalsIgnoreCase(type)) {
            return fixedAmountDiscountStrategy;
        } else if ("percentage".equalsIgnoreCase(type)) {
            return percentageDiscountStrategy;
        }
        throw new IllegalArgumentException("Invalid discount type: " + type);
    }
}
