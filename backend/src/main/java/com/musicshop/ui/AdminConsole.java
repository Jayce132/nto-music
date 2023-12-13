package com.musicshop.ui;

import com.musicshop.config.ApplicationContext;
import com.musicshop.controller.product.ProductController;
import com.musicshop.model.product.Product;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;

public class AdminConsole {

    private final ProductController productController;

    public AdminConsole(ApplicationContext context) {
        this.productController = context.getProductController();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean continueRunning = true;

        while (continueRunning) {
            System.out.println("\nAdmin Console - Product Management");
            System.out.println("1. View All Products");
            System.out.println("2. Update Product");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAllProducts();
                    break;
                case 2:
                    updateProduct(scanner);
                    break;
                case 3:
                    continueRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAllProducts() {
        for (Product product : productController.listAllProducts()) {
            System.out.println(product.getId() + ": " + product.getName() + " - " + product.getPrice());
        }
    }

    private void updateProduct(Scanner scanner) {
        System.out.print("Enter Product ID to update: ");
        Long productId = scanner.nextLong();
        Optional<Product> productOpt = productController.getProductById(productId);

        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            System.out.println("Current Product Info: " + product.getName() + " - " + product.getPrice());

            System.out.print("Enter new name (or press Enter to skip): ");
            scanner.nextLine(); // Consume leftover newline
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                product.setName(name);
            }

//            System.out.print("Enter new description (or press Enter to skip): ");
//            String description = scanner.nextLine();
//            if (!description.isEmpty()) {
//                product.setDescription(description);
//            }
//
            System.out.print("Enter new price (or -1 to skip): ");
            BigDecimal price = scanner.nextBigDecimal();
            if (price.compareTo(BigDecimal.valueOf(-1)) != 0) {
                product.setPrice(price);
            }
//
//            System.out.print("Enter new quantity available (or -1 to skip): ");
//            int quantity = scanner.nextInt();
//            if (quantity != -1) {
//                product.setQuantityAvailable(quantity);
//            }

            productController.updateProduct(product);
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product not found.");
        }
    }
}
