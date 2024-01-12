package com.musicshop.validation.product;

import com.musicshop.model.product.Product;
import javax.xml.bind.ValidationException;

public class CategoryValidationDecorator extends ProductValidatorDecorator {
    public CategoryValidationDecorator(ProductValidator decoratedValidator) {
        super(decoratedValidator);
    }

    @Override
    public void validate(Product product) throws ValidationException {
        super.validate(product);
        if (product.getCategory() == null) {
            throw new ValidationException("Product category is required.");
        }
    }
}
