# Music Shop Application

## Functionalities & API Endpoints

### Common Features
- **User Authentication**: Allows users to log in as either regular users or admins.
- **Product Exploration**: View and interact with product listings.
    - **Home Page**: Products listed using `SimpleProductDTO`.
    - **Product Details**: On click, shows detailed product information with `DetailedProductDTO`.

### User Specific
- **Shopping Cart Management**: Users can add products to their cart, ensuring the quantity added does not exceed available stock.
- **User Profile Management**: Users have the ability to edit their profile information.
- **Cart Change Notifications**: Users receive notifications for changes to products in their cart (update, delete, or apply discounts).

### Admin Specific
- **Product Management**: Admins can add, edit, delete products, and apply discounts.
- **Custom Validation Messages**: Implemented for product data to ensure data accuracy.

## API Endpoints

### Product Management
- **GET** `/api/products`: List all products.
- **POST** `/api/products`: Add a new product.
- **GET** `/api/products/{id}`: Get product details.
- **PUT** `/api/products/{id}`: Update product information.
- **PATCH** `/api/products/{id}`: Partially update product.
- **DELETE** `/api/products/{id}`: Delete a product.
- **PATCH** `/api/products/{id}/apply-discount`: Apply discount to a product.

### Cart Management
- **POST** `/api/carts/{userId}/products/{productId}`: Add product to user's cart.
- **GET** `/api/carts/{cartId}/products/{productId}`: Retrieve a specific cart item.
- **GET** `/api/carts/{cartId}/details`: List all items in a cart.
- **PUT** `/api/carts/details/{detailId}`: Update a cart item.
- **DELETE** `/api/carts/details/{detailId}`: Remove an item from the cart.

### User Management
- **GET** `/api/users/{userId}`: Retrieve user details.
- **PUT** `/api/users/{userId}`: Update user details.

### Notification System
- **GET** `/api/notifications/user/{userId}`: Get notifications for a user.
- **DELETE** `/api/notifications/{notificationId}`: Delete a notification.

## Design Patterns
1. **Builder & Factory**: For creating detailed and simple product representations.
2. **Observer**: To notify users about changes in products related to their cart.
3. **Factory & Strategy**: Applied in discount application logic.
4. **Singleton**: Used in Spring for dependency injection.
5. **Decorator**: Enhances product validation logic.
    - Each validation layer adds specific checks (e.g., name format, price range) on top of the basic validation.

## Technical Aspects
- **JPA (Java Persistence API)**: Manages database operations.
- **Mockito**: Employed for service layer testing.
- **Swagger**: API documentation available at `http://localhost:8080/swagger-ui/index.html`.
- **React with Spring Boot**: Frontend developed using React, integrated with a Spring Boot backend.
