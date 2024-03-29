package com.musicshop.dto.product;

import com.musicshop.dto.product.SimpleProductDTO;
import com.musicshop.dto.product.DetailedProductDTO;
import com.musicshop.model.product.Product;

public class ProductDTOFactory {

    public static SimpleProductDTO createSimpleProductDTO(Product product) {
        return new SimpleProductDTO.Builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    public static DetailedProductDTO createDetailedProductDTO(Product product) {
        return new DetailedProductDTO.Builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantityAvailable(product.getQuantityAvailable())
                .categoryName(product.getCategory().getCategoryName())
                .build();
    }
}
