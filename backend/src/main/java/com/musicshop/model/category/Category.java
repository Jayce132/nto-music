package com.musicshop.model.category;

import com.musicshop.model.BaseModel;

public class Category extends BaseModel<Long> {
    private Long parentCategoryID;
    private String categoryName;

    public Long getParentCategoryID() {
        return parentCategoryID;
    }

    public void setParentCategoryID(Long parentCategoryID) {
        this.parentCategoryID = parentCategoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
