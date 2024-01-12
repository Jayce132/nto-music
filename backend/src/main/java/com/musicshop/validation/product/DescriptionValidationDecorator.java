package com.musicshop.validation.product;

import com.musicshop.model.product.Product;
import javax.xml.bind.ValidationException;

public class DescriptionValidationDecorator extends ProductValidatorDecorator {
    public DescriptionValidationDecorator(ProductValidator decoratedValidator) {
        super(decoratedValidator);
    }

    @Override
    public void validate(Product product) throws ValidationException {
        super.validate(product);
        if (product.getDescription() == null || product.getDescription().trim().isEmpty()) {
            throw new ValidationException("Product description is required.");
        }
    }
}

