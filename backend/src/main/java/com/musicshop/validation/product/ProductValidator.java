package com.musicshop.validation.product;

import com.musicshop.model.product.Product;

import javax.xml.bind.ValidationException;

public interface ProductValidator {
    void validate(Product product) throws ValidationException;
}

