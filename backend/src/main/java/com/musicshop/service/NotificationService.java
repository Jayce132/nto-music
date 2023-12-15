package com.musicshop.service;

import com.musicshop.model.product.Product;
import com.musicshop.event.product.ProductUpdateEvent;
import com.musicshop.model.cart.CartDetail;
import com.musicshop.repository.cart.CartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final CartDetailRepository cartDetailRepository;

    @Autowired
    public NotificationService(CartDetailRepository cartDetailRepository) {
        this.cartDetailRepository = cartDetailRepository;
    }

    @EventListener
    public void onProductUpdate(ProductUpdateEvent event) {
        Product updatedProduct = event.getUpdatedProduct();
        Long updatedProductId = updatedProduct.getId();

        // Retrieve all cart details containing the updated product
        List<CartDetail> affectedCartDetails = cartDetailRepository.findByProductId(updatedProductId);

        // Check if there are any carts containing the updated product
        if (!affectedCartDetails.isEmpty()) {
            // Output the updated product details
            System.out.println("\nPRODUCT UPDATED IN CART: " + updatedProduct.getName() +
                    " Price: " + updatedProduct.getPrice() +
                    " Description: " + updatedProduct.getDescription() + "\n");
        }
    }
}
