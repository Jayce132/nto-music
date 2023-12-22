package com.musicshop.validation.product;

import com.musicshop.model.product.Product;

import javax.xml.bind.ValidationException;

public abstract class ProductValidatorDecorator implements ProductValidator {
    protected ProductValidator decoratedValidator;

    public ProductValidatorDecorator(ProductValidator decoratedValidator) {
        this.decoratedValidator = decoratedValidator;
    }

    public void validate(Product product) throws ValidationException {
        decoratedValidator.validate(product);
    }
}

