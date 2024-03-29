package com.musicshop.controller.product;

import com.musicshop.dto.product.DetailedProductDTO;
import com.musicshop.dto.product.SimpleProductDTO;
import com.musicshop.dto.product.ProductDTOFactory;
import com.musicshop.model.product.Product;
import com.musicshop.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<SimpleProductDTO> listAllProducts() {
        // When I list the products I will use their simple representation
        return productService.listAllProducts().stream().map(ProductDTOFactory::createSimpleProductDTO).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        // Used wildcard because response can have a Product or a String containing the error message
        try {
            Product newProduct = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedProductDTO> getProductById(@PathVariable Long id) {
        // Here I use the more detailed DTO
        return productService.getProductById(id).map(product -> ResponseEntity.ok(ProductDTOFactory.createDetailedProductDTO(product))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailedProductDTO> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        // Here I use the more detailed DTO
        return productService.updateProduct(id, productDetails).map(product -> ResponseEntity.ok(ProductDTOFactory.createDetailedProductDTO(product))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    // Used this for partial updates,
    // because PUT expects a full replace and for partial fields it will make the other values null
    public ResponseEntity<DetailedProductDTO> partialUpdateProduct(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return productService.partialUpdateProduct(id, updates).map(product -> ResponseEntity.ok(ProductDTOFactory.createDetailedProductDTO(product))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        boolean exists = productService.getProductById(id).isPresent();
        if (exists) {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();  // Return a successful response
        } else {
            return ResponseEntity.notFound().build();  // Return a 404 if the product does not exist
        }
    }

    @PatchMapping("/{id}/apply-discount")
    public ResponseEntity<?> applyDiscount(@PathVariable Long id, @RequestParam String discountType) {
        return productService.applyDiscount(id, discountType)
                .map(product -> ResponseEntity.ok(ProductDTOFactory.createDetailedProductDTO(product)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
