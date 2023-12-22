package com.musicshop.validation.product;

import com.musicshop.model.product.Product;

import javax.xml.bind.ValidationException;
import java.math.BigDecimal;

public class PriceValidationDecorator extends ProductValidatorDecorator {
    public PriceValidationDecorator(ProductValidator decoratedValidator) {
        super(decoratedValidator);
    }

    @Override
    public void validate(Product product) throws ValidationException {
        super.validate(product); // validate using the basic validator or previous decorator
        if (product.getPrice().compareTo(new BigDecimal("10000")) > 0) {
            throw new ValidationException("Product price is unrealistically high.");
        }
    }
}
