package com.musicshop.validation.product;

import com.musicshop.model.product.Product;

import javax.xml.bind.ValidationException;
import java.math.BigDecimal;

public class BasicProductValidator implements ProductValidator {
    @Override
    public void validate(Product product) throws ValidationException {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new ValidationException("Product name is required.");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Product price must be greater than 0.");
        }
    }
}
