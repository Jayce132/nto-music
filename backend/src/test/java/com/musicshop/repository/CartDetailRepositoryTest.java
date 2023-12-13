package com.musicshop.repository;

import com.musicshop.model.cart.CartDetail;
import com.musicshop.repository.cart.CartDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartDetailRepositoryTest {

    private CartDetailRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new CartDetailRepository();
    }

    @Test
    public void testAddAndRetrieveCartDetail() {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setCartId(1L);
        cartDetail.setProductId(2L);
        cartDetail.setQuantity(3);

        repository.save(cartDetail);

        Optional<CartDetail> retrievedDetail = repository.findById(cartDetail.getId());
        assertTrue(retrievedDetail.isPresent());
        assertEquals(cartDetail.getProductId(), retrievedDetail.get().getProductId());
        assertEquals(cartDetail.getQuantity(), retrievedDetail.get().getQuantity());
    }

    @Test
    public void testFindByCartIdAndProductId() {
        CartDetail cartDetail1 = new CartDetail();
        cartDetail1.setCartId(1L);
        cartDetail1.setProductId(2L);
        cartDetail1.setQuantity(3);
        repository.save(cartDetail1);

        CartDetail cartDetail2 = new CartDetail();
        cartDetail2.setCartId(1L);
        cartDetail2.setProductId(1L);
        cartDetail2.setQuantity(5);
        repository.save(cartDetail2);

        Optional<CartDetail> result = repository.findByCartIdAndProductId(1L, 1L);
        assertTrue(result.isPresent());
        assertEquals(cartDetail2.getProductId(), result.get().getProductId());
        assertEquals(cartDetail2.getQuantity(), result.get().getQuantity());
    }
}
