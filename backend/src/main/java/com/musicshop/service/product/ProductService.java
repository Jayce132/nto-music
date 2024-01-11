package com.musicshop.service.product;

import com.musicshop.model.product.Product;
import com.musicshop.event.product.ProductUpdateEvent;
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

    @Autowired
    public ProductService(ProductRepository productRepository, ApplicationEventPublisher eventPublisher) {
        this.productRepository = productRepository;
        this.eventPublisher = eventPublisher;
    }

    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) throws ValidationException {
        ProductValidator validator =
                new PriceValidationDecorator(
                        new NameValidationDecorator(
                                new BasicProductValidator()));


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
        productRepository.deleteById(id);
    }
}
