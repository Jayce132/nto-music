package com.musicshop.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.musicshop.model.category.Category;
import com.musicshop.model.customer.Customer;
import com.musicshop.model.product.Product;
import com.musicshop.model.cart.Cart;
import com.musicshop.repository.category.CategoryRepository;
import com.musicshop.repository.customer.CustomerRepository;
import com.musicshop.repository.product.ProductRepository;
import com.musicshop.repository.cart.CartRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create and save categories
        Category instruments = new Category();
        instruments.setCategoryName("Instruments");
        categoryRepository.save(instruments);

        Category guitars = new Category();
        guitars.setCategoryName("Guitars");
        guitars.setParentCategory(instruments);
        categoryRepository.save(guitars);

        // Create and save products
        Product product1 = new Product();
        product1.setName("Martin LX1E");
        product1.setDescription("Portable and sounds great");
        product1.setPrice(new BigDecimal("550.99"));
        product1.setQuantityAvailable(10);
        product1.setCategory(guitars);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Fender Player Stratocaster");
        product2.setDescription("Classic sound and look");
        product2.setPrice(new BigDecimal("649.99"));
        product2.setQuantityAvailable(8);
        product2.setCategory(guitars);
        productRepository.save(product2);

        // Create and save customer and cart
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("1234567890");
        customerRepository.save(customer);

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setDateCreated(LocalDateTime.now());
        cartRepository.save(cart);
    }
}
