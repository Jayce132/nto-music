package com.musicshop.service.product;

import com.musicshop.discount.DiscountStrategy;
import com.musicshop.discount.DiscountStrategyFactory;
import com.musicshop.event.product.ProductDeletionEvent;
import com.musicshop.event.product.ProductDiscountEvent;
import com.musicshop.model.cart.CartDetail;
import com.musicshop.model.product.Product;
import com.musicshop.event.product.ProductUpdateEvent;
import com.musicshop.repository.cart.CartDetailRepository;
import com.musicshop.repository.product.ProductRepository;
import com.musicshop.validation.product.BasicProductValidator;
import com.musicshop.validation.product.NameValidationDecorator;
import com.musicshop.validation.product.PriceValidationDecorator;
import com.musicshop.validation.product.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationEventPublisher;

import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final DiscountStrategyFactory discountStrategyFactory;
    private final CartDetailRepository cartDetailRepository;


    @Autowired
    public ProductService(ProductRepository productRepository, ApplicationEventPublisher eventPublisher,
                          DiscountStrategyFactory discountStrategyFactory, CartDetailRepository cartDetailRepository) {
        this.productRepository = productRepository;
        this.eventPublisher = eventPublisher;
        this.discountStrategyFactory = discountStrategyFactory;
        this.cartDetailRepository = cartDetailRepository;
    }

    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) throws ValidationException {
        ProductValidator validator = new PriceValidationDecorator(new NameValidationDecorator(new BasicProductValidator()));


        validator.validate(product);

        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id).map(product -> {
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setPrice(productDetails.getPrice());
            product.setQuantityAvailable(productDetails.getQuantityAvailable());
            product.setCategory(productDetails.getCategory());

            Product updatedProduct = productRepository.save(product);
            eventPublisher.publishEvent(new ProductUpdateEvent(this, updatedProduct));
            return updatedProduct;
        });
    }

    public Optional<Product> partialUpdateProduct(Long id, Map<String, Object> updates) {
        return productRepository.findById(id).map(product -> {
            applyPartialUpdates(product, updates);

            Product updatedProduct = productRepository.save(product);
            eventPublisher.publishEvent(new ProductUpdateEvent(this, updatedProduct));
            return updatedProduct;
        });
    }

    private void applyPartialUpdates(Product product, Map<String, Object> updates) {
        if (updates.containsKey("name")) {
            product.setName((String) updates.get("name"));
        }
        if (updates.containsKey("price")) {
            product.setPrice(new BigDecimal(String.valueOf(updates.get("price"))));
        }
        // Will add similar conditions for other fields that can be updated
        // and are in the updates map
    }


    public void deleteProduct(Long id) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            List<CartDetail> cartDetails = cartDetailRepository.findByProductId(id);
            cartDetailRepository.deleteAll(cartDetails); // Delete cart details first
            productRepository.delete(product); // Then delete the product
            eventPublisher.publishEvent(new ProductDeletionEvent(this, product, cartDetails));
        } else {
            throw new RuntimeException("Product not found");
        }
    }


    public Optional<Product> applyDiscount(Long productId, String discountType) {
        return productRepository.findById(productId).map(product -> {
            BigDecimal originalPrice = product.getPrice();
            DiscountStrategy discountStrategy = discountStrategyFactory.getDiscountStrategy(discountType);
            BigDecimal discountedPrice = discountStrategy.applyDiscount(product);
            product.setPrice(discountedPrice);
            Product savedProduct = productRepository.save(product);

            // Publish the discount event
            eventPublisher.publishEvent(new ProductDiscountEvent(savedProduct, originalPrice));

            return savedProduct;
        });
    }
}
