package com.musicshop.ui;

import com.musicshop.config.ApplicationContext;
import com.musicshop.controller.cart.CartController;
import com.musicshop.controller.product.ProductController;
import com.musicshop.discount.DiscountStrategy;
import com.musicshop.model.cart.Cart;
import com.musicshop.model.cart.CartDetail;
import com.musicshop.model.product.Product;

import java.util.Scanner;
import java.util.Optional;

public class MusicShopConsole {

    private final ProductController productController;
    private final CartController cartController;

    private final DiscountStrategy discountStrategy;

    private Cart currentCart = null;


    public MusicShopConsole(ApplicationContext context) {
        this.productController = context.getProductController();
        this.cartController = context.getCartController();
        this.discountStrategy = context.getDiscountStrategy();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean continueRunning = true;

        while (continueRunning) {
            System.out.println("\nWelcome to the Music Shop!");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Edit Cart Item");
            System.out.println("5. Delete Cart Item");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    System.out.print("Enter Product ID to add to cart: ");
                    Long productId = scanner.nextLong();
                    addToCart(productId);
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    editCart();
                    break;
                case 5:
                    deleteFromCart();
                    break;
                case 6:
                    checkout();
                    break;
                case 7:
                    continueRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Thank you for shopping with us!");
    }

    private void viewProducts() {
        for (Product product : productController.listAllProducts()) {
            System.out.println(product.getId() + ": " + product.getName() +
                    " - Original Price: " + product.getPrice() +
                    " - Discounted Price: " + discountStrategy.applyDiscount(product));
        }
    }

    private void addToCart(Long productId) {
        Optional<Product> productOpt = productController.getProductById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            if (currentCart == null) {
                currentCart = cartController.createNewCart();
            }
            cartController.addProductToCart(currentCart, productId, quantity);
            System.out.println(quantity + " x " + product.getName() + " added to cart.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private void editCart() {
        Scanner scanner = new Scanner(System.in);
        viewCart();
        System.out.print("Enter the Product ID to edit: ");
        Long productId = scanner.nextLong();

        Optional<CartDetail> cartDetailOpt = cartController.getCartDetailByCartIdAndProductId(currentCart.getId(), productId);
        if (cartDetailOpt.isPresent()) {
            CartDetail cartDetail = cartDetailOpt.get();
            System.out.print("Enter the new quantity: ");
            int newQuantity = scanner.nextInt();
            if (newQuantity <= 0) {
                cartController.deleteCartDetail(cartDetail.getId());
                System.out.println("Product removed from cart.");
            } else {
                cartController.updateCartDetailQuantity(cartDetail.getId(), newQuantity);
                System.out.println("Cart updated.");
            }
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    private void deleteFromCart() {
        Scanner scanner = new Scanner(System.in);
        viewCart();
        System.out.print("Enter the Product ID to delete: ");
        Long productId = scanner.nextLong();

        Optional<CartDetail> cartDetailOpt = cartController.getCartDetailByCartIdAndProductId(currentCart.getId(), productId);
        if (cartDetailOpt.isPresent()) {
            cartController.deleteCartDetail(cartDetailOpt.get().getId());
            System.out.println("Product removed from cart.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    private void viewCart() {
        if (currentCart == null) {
            System.out.println("Your cart is empty.");
            return;
        }
        for (CartDetail cartDetail : cartController.listAllCartDetails()) {
            if (cartDetail.getCartId().equals(currentCart.getId())) {
                Optional<Product> productOpt = productController.getProductById(cartDetail.getProductId());
                if (productOpt.isPresent()) {
                    Product product = productOpt.get();
                    System.out.println(product.getId() + ": " + product.getName() + " - " + product.getPrice() + " x " + cartDetail.getQuantity());
                }
            }
        }
    }

    private void checkout() {
        if (currentCart == null) {
            System.out.println("Your cart is empty. Nothing to checkout.");
            return;
        }
        System.out.println("Order placed successfully!");
        currentCart = null;  // Reset the cart after checkout
    }
}