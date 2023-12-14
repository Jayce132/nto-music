package com.musicshop.controller.cart;

import com.musicshop.model.cart.Cart;
import com.musicshop.model.cart.CartDetail;
import com.musicshop.model.product.Product;
import com.musicshop.repository.cart.CartDetailRepository;
import com.musicshop.repository.cart.CartRepository;
import com.musicshop.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartController(CartRepository cartRepository,
                          CartDetailRepository cartDetailRepository,
                          ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.productRepository = productRepository;
    }

    @PostMapping
    public Cart createNewCart(@RequestBody Cart cart) {
        cart.setDateCreated(LocalDateTime.now());
        return cartRepository.save(cart);
    }

    @PostMapping("/{cartId}/products/{productId}")
    public CartDetail addProductToCart(@PathVariable Long cartId,
                                       @PathVariable Long productId,
                                       @RequestParam int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();
        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        cartDetail.setQuantity(quantity);
        return cartDetailRepository.save(cartDetail);
    }

    @GetMapping("/{cartId}/products/{productId}")
    public ResponseEntity<CartDetail> getCartDetail(@PathVariable Long cartId, @PathVariable Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();
        return cartDetailRepository.findByCartAndProduct(cart, product)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{cartId}/details")
    public ResponseEntity<List<CartDetail>> listCartDetails(@PathVariable Long cartId) {
        return cartRepository.findById(cartId)
                .map(cart -> {
                    List<CartDetail> cartDetails = cartDetailRepository.findAll();
                    cartDetails.removeIf(detail -> !detail.getCart().getId().equals(cartId));
                    return ResponseEntity.ok(cartDetails);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/details/{detailId}")
    public CartDetail updateCartDetail(@PathVariable Long detailId,
                                       @RequestParam int newQuantity) {
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
