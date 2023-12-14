package com.musicshop.model.product;

import com.musicshop.model.BaseModel;
import com.musicshop.model.category.Category;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
public class Product extends BaseModel<Long> {

    private String name;
    private String description;
    private BigDecimal price;
    private int quantityAvailable;

    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
