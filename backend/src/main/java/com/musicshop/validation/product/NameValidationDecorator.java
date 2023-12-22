package com.musicshop.validation.product;

import com.musicshop.model.product.Product;

import javax.xml.bind.ValidationException;

public class NameValidationDecorator extends ProductValidatorDecorator {
    public NameValidationDecorator(ProductValidator decoratedValidator) {
        super(decoratedValidator);
    }

    @Override
    public void validate(Product product) throws ValidationException {
        super.validate(product); // validate using the basic validator
        if (!product.getName().matches("^[a-zA-Z0-9 ]+$")) {
            throw new ValidationException("Product name contains invalid characters.");
        }
    }
}
