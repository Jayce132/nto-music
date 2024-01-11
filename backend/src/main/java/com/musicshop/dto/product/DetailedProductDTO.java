package com.musicshop.dto.product;

import java.math.BigDecimal;

public class DetailedProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantityAvailable;
    private String categoryName;

    // Private constructor to prevent instantiation without builder
    private DetailedProductDTO() {
    }

    // Here I am using getters instead of @JsonProperty("fieldName")
    // at least one of the two is needed for JSON serialization
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public String getCategoryName() {
        return categoryName;
    }

    // Builder inner class
    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private BigDecimal price;
        private String categoryName;
        private Integer quantityAvailable;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder quantityAvailable(Integer quantityAvailable) {
            this.quantityAvailable = quantityAvailable;
            return this;
        }

        public Builder categoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public DetailedProductDTO build() {
            DetailedProductDTO dto = new DetailedProductDTO();
            dto.id = this.id;
            dto.name = this.name;
            dto.description = this.description;
            dto.price = this.price;
            dto.quantityAvailable = this.quantityAvailable;
            dto.categoryName = this.categoryName;
            return dto;
        }
    }
}
