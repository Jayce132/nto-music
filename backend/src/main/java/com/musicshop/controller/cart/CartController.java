package com.musicshop.controller.cart;

import com.musicshop.model.cart.Cart;
import com.musicshop.model.cart.CartDetail;
import com.musicshop.model.user.User;
import com.musicshop.model.product.Product;
import com.musicshop.repository.cart.CartDetailRepository;
import com.musicshop.repository.cart.CartRepository;
import com.musicshop.repository.user.UserRepository;
import com.musicshop.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/carts")
public class CartController {

    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public CartController(CartRepository cartRepository, CartDetailRepository cartDetailRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    private Cart createNewCart(User user) {
        Cart newCart = new Cart();
        newCart.setUser(user);
        newCart.setDateCreated(LocalDateTime.now());
        return cartRepository.save(newCart);
    }

    @PostMapping("/{userId}/products/{productId}")
    public ResponseEntity<?> addProductToCart(@PathVariable Long userId, @PathVariable Long productId, @RequestParam int quantity) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user).orElseGet(() -> createNewCart(user));

        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        if (quantity > product.getQuantityAvailable()) {
            return ResponseEntity.badRequest().body("Not enough quantity available");
        }

        // Update product quantity
        product.setQuantityAvailable(product.getQuantityAvailable() - quantity);
        productRepository.save(product);

        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        cartDetail.setQuantity(quantity);
        cartDetailRepository.save(cartDetail);

        return ResponseEntity.ok().body("Product added to cart");
    }


    @GetMapping("/{cartId}/products/{productId}")
    public ResponseEntity<CartDetail> getCartDetail(@PathVariable Long cartId, @PathVariable Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();
        return cartDetailRepository.findByCartAndProduct(cart, product).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{cartId}/details")
    public ResponseEntity<List<CartDetail>> listCartDetails(@PathVariable Long cartId) {
        return cartRepository.findById(cartId).map(cart -> {
            List<CartDetail> cartDetails = cartDetailRepository.findAll();
            cartDetails.removeIf(detail -> !detail.getCart().getId().equals(cartId));
            return ResponseEntity.ok(cartDetails);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/details/{detailId}")
    public CartDetail updateCartDetail(@PathVariable Long detailId, @RequestParam int newQuantity) {
        CartDetail cartDetail = cartDetailRepository.findById(detailId).orElseThrow();
        cartDetail.setQuantity(newQuantity);
        return cartDetailRepository.save(cartDetail);
    }

    @DeleteMapping("/details/{detailId}")
    public ResponseEntity<?> deleteCartDetail(@PathVariable Long detailId) {
        cartDetailRepository.deleteById(detailId);
        return ResponseEntity.ok().build();
    }
}
