package com.musicshop.discount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiscountStrategyFactoryTest {

    @Test
    public void testStrategyCreation() {
        DiscountStrategy percentageStrategy = DiscountStrategyFactory.createDiscountStrategy(DiscountType.PERCENTAGE, 10);
        DiscountStrategy fixedStrategy = DiscountStrategyFactory.createDiscountStrategy(DiscountType.FIXED, 50);

        Assertions.assertInstanceOf(PercentageDiscountStrategy.class, percentageStrategy, "Should be a PercentageDiscountStrategy");
        Assertions.assertInstanceOf(FixedAmountDiscountStrategy.class, fixedStrategy, "Should be a FixedAmountDiscountStrategy");
    }
}

