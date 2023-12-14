package com.musicshop.repository.category;

import com.musicshop.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Implement specific methods for Category
}
