package com.musicshop.validation.product;

import com.musicshop.model.product.Product;
import javax.xml.bind.ValidationException;

public class QuantityAvailableValidationDecorator extends ProductValidatorDecorator {
    public QuantityAvailableValidationDecorator(ProductValidator decoratedValidator) {
        super(decoratedValidator);
    }

    @Override
    public void validate(Product product) throws ValidationException {
        super.validate(product);
        if (product.getQuantityAvailable() <= 0) {
            throw new ValidationException("There must be at least one product.");
        }
    }
}
