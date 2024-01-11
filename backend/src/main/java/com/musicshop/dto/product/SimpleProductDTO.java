package com.musicshop.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class SimpleProductDTO {
    @JsonProperty("id")
    protected Long id;
    @JsonProperty("name")
    protected String name;
    @JsonProperty("price")
    protected BigDecimal price;

    // Private constructor to prevent instantiation without builder
    protected SimpleProductDTO() {
    }

    // Builder inner class
    public static class Builder {
        protected Long id;
        protected String name;
        protected BigDecimal price;

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

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public SimpleProductDTO build() {
            SimpleProductDTO dto = new SimpleProductDTO();
            dto.id = this.id;
            dto.name = this.name;
            dto.price = this.price;
            return dto;
        }
    }
}
