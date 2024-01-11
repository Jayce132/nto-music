package com.musicshop.model.category;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.musicshop.model.BaseModel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseModel<Long> {

    @ManyToOne
    @JoinColumn(name = "ParentCategoryID")
    @JsonBackReference
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    @JsonManagedReference
    private Set<Category> subCategories = new HashSet<>();


    private String categoryName;

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}