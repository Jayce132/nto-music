package com.musicshop.event.product;

import com.musicshop.model.product.Product;
import com.musicshop.model.cart.CartDetail;
import org.springframework.context.ApplicationEvent;
import java.util.List;

public class ProductDeletionEvent extends ApplicationEvent {
    private final Product deletedProduct;
    private final List<CartDetail> affectedCartDetails;

    public ProductDeletionEvent(Object source, Product deletedProduct, List<CartDetail> affectedCartDetails) {
        super(source);
        this.deletedProduct = deletedProduct;
        this.affectedCartDetails = affectedCartDetails;
    }

    public Product getDeletedProduct() {
        return deletedProduct;
    }

    public List<CartDetail> getAffectedCartDetails() {
        return affectedCartDetails;
    }
}
