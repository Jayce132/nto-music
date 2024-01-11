package com.musicshop.service;

import com.musicshop.model.product.Product;
import com.musicshop.event.product.ProductUpdateEvent;
import com.musicshop.model.cart.CartDetail;
import com.musicshop.model.user.Notification;
import com.musicshop.repository.cart.CartDetailRepository;
import com.musicshop.repository.user.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final CartDetailRepository cartDetailRepository;
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(CartDetailRepository cartDetailRepository, NotificationRepository notificationRepository) {
        this.cartDetailRepository = cartDetailRepository;
        this.notificationRepository = notificationRepository;
    }

    @EventListener
    public void onProductUpdate(ProductUpdateEvent event) {
        Product updatedProduct = event.getUpdatedProduct();
        Long updatedProductId = updatedProduct.getId();

        List<CartDetail> affectedCartDetails = cartDetailRepository.findByProductId(updatedProductId);

        affectedCartDetails.forEach(cartDetail -> {
            String message = String.format("Product updated in cart: %s, Price: %s",
                    updatedProduct.getName(), updatedProduct.getPrice());

            Notification notification = new Notification();
            notification.setTimestamp(LocalDateTime.now());
            notification.setMessage(message);
            notification.setUser(cartDetail.getCart().getUser());

            notificationRepository.save(notification);
        });
    }
}

