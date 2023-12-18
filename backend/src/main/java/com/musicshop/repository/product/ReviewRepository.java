package com.musicshop.repository.product;

import com.musicshop.model.product.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Implement specific methods for Review
}
