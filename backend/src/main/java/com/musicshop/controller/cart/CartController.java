package com.musicshop.controller.cart;

import com.musicshop.event.product.ProductUpdateEvent;
import com.musicshop.event.product.ProductUpdateListener;
import com.musicshop.model.cart.Cart;
import com.musicshop.model.cart.CartDetail;
import com.musicshop.model.product.Product;
import com.musicshop.repository.cart.CartDetailRepository;
import com.musicshop.repository.cart.CartRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CartController implements ProductUpdateListener {

    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;

    public CartController(CartRepository cartRepository, CartDetailRepository cartDetailRepository) {
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
    }

    public Cart createNewCart() {
        Cart cart = new Cart();
        // Don't forget to change this
        cart.setCustomerId(1L);
        cart.setDateCreated(LocalDateTime.now());
        return cartRepository.save(cart);
    }

    public void addProductToCart(Cart cart, Long productId, int quantity) {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setCartId(cart.getId());
        cartDetail.setProductId(productId);
        cartDetail.setQuantity(quantity);
        cartDetailRepository.save(cartDetail);
    }

    public Optional<CartDetail> getCartDetailByCartIdAndProductId(Long cartId, Long productId) {
        return cartDetailRepository.findByCartIdAndProductId(cartId, productId);
    }

    public List<CartDetail> listAllCartDetails() {
        return cartDetailRepository.findAll();
    }

    public void updateCartDetailQuantity(Long cartDetailId, int newQuantity) {
        Optional<CartDetail> cartDetailOpt = cartDetailRepository.findById(cartDetailId);
        if (cartDetailOpt.isPresent()) {
            CartDetail cartDetail = cartDetailOpt.get();
            cartDetail.setQuantity(newQuantity);
            cartDetailRepository.save(cartDetail);
        }
    }

    public void deleteCartDetail(Long cartDetailId) {
        cartDetailRepository.deleteById(cartDetailId);
    }

    @Override
    public void onProductUpdate(ProductUpdateEvent event) {
        Product updatedProduct = event.getUpdatedProduct();
        List<CartDetail> allCartDetails = cartDetailRepository.findAll();

        for (CartDetail cartDetail : allCartDetails) {
            if (cartDetail.getProductId().equals(updatedProduct.getId())) {
                System.out.println("Product " + updatedProduct.getName() + " in your cart has been updated");
            }
        }
    }
}
