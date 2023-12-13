package com.musicshop.config;

import com.musicshop.controller.cart.CartController;
import com.musicshop.controller.product.ProductController;
import com.musicshop.discount.DiscountStrategy;
import com.musicshop.discount.DiscountStrategyFactory;
import com.musicshop.discount.DiscountType;
import com.musicshop.discount.PercentageDiscountStrategy;
import com.musicshop.model.cart.Cart;
import com.musicshop.model.product.Product;
import com.musicshop.repository.cart.CartDetailRepository;
import com.musicshop.repository.cart.CartRepository;
import com.musicshop.repository.product.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ApplicationContext {

    private static ApplicationContext instance;

    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final ProductRepository productRepository;

    private final CartController cartController;
    private final ProductController productController;

    private final DiscountStrategy discountStrategy;

    private ApplicationContext() {
        // Initialize repositories
        cartRepository = new CartRepository();
        cartDetailRepository = new CartDetailRepository();
        productRepository = new ProductRepository();

        // Initialize controllers with the respective repositories
        cartController = new CartController(cartRepository, cartDetailRepository);
        productController = new ProductController(productRepository);

        // Initialize discount strategy
        discountStrategy = DiscountStrategyFactory.createDiscountStrategy(DiscountType.PERCENTAGE, 10);

        // Register listeners
        productController.registerProductUpdateListener(cartController);

        // Initialize default data
        initializeDefaultData();
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    private void initializeDefaultData() {
        // Creating and adding products
        Product product1 = new Product();
        product1.setName("Martin LX1E");
        product1.setDescription("Portable and sounds great");
        product1.setPrice(new BigDecimal("550.99"));
        product1.setQuantityAvailable(10);
        product1.setCategoryId(2L);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Fender Player Stratocaster");
        product2.setDescription("Classic sound and look");
        product2.setPrice(new BigDecimal("649.99"));
        product2.setQuantityAvailable(8);
        product2.setCategoryId(3L);
        productRepository.save(product2);

        // Creating and adding carts
        Cart cart1 = new Cart();
        cart1.setCustomerId(2L);
        cart1.setDateCreated(LocalDateTime.parse("2023-10-15T00:00:00"));
        cartRepository.save(cart1);
    }

    public CartController getCartController() {
        return cartController;
    }

    public ProductController getProductController() {
        return productController;
    }

    public DiscountStrategy getDiscountStrategy() {
        return discountStrategy;
    }

    // Add getters for other controllers and repositories
}

