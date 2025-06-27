//package com.musicshop.cart;
//
//import com.musicshop.model.cart.Cart;
//import com.musicshop.model.cart.CartDetail;
//import com.musicshop.model.product.Product;
//import com.musicshop.model.user.User;
//import com.musicshop.repository.cart.CartDetailRepository;
//import com.musicshop.repository.cart.CartRepository;
//import com.musicshop.repository.product.ProductRepository;
//import com.musicshop.repository.user.UserRepository;
//import com.musicshop.service.cart.CartService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//class CartServiceTest {
//
//    @Mock
//    private CartRepository cartRepository;
//    @Mock
//    private CartDetailRepository cartDetailRepository;
//    @Mock
//    private ProductRepository productRepository;
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private CartService cartService;
//
//    private User user;
//    private Product product;
//    private Cart cart;
//
//    @BeforeEach
//    void setUp() {
//        user = new User();
//        user.setId(1L);
//
//        product = new Product();
//        product.setId(1L);
//        product.setQuantityAvailable(10);
//
//        cart = new Cart();
//        cart.setUser(user);
//        cart.setDateCreated(LocalDateTime.now());
//    }
//
//    @Test
//    void addProductToCart_Success() {
//        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
//        when(cartRepository.findByUser(user)).thenReturn(Optional.of(cart));
//        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
//
//        CartDetail cartDetail = cartService.addProductToCart(user.getId(), product.getId(), 5);
//
//        assertNotNull(cartDetail);
//        assertEquals(5, cartDetail.getQuantity());
//        verify(cartDetailRepository).save(any(CartDetail.class));
//    }
//
//    @Test
//    void addProductToCart_ProductNotAvailable() {
//        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
//        when(cartRepository.findByUser(user)).thenReturn(Optional.of(cart));
//        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
//
//        assertThrows(RuntimeException.class, () -> {
//            cartService.addProductToCart(user.getId(), product.getId(), 15);
//        });
//    }
//
//    @Test
//    void getCartDetail_Found() {
//        CartDetail cartDetail = new CartDetail();
//        cartDetail.setCart(cart);
//        cartDetail.setProduct(product);
//        cartDetail.setQuantity(5);
//
//        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
//        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
//        when(cartDetailRepository.findByCartAndProduct(cart, product)).thenReturn(Optional.of(cartDetail));
//
//        Optional<CartDetail> foundCartDetail = cartService.getCartDetail(cart.getId(), product.getId());
//
//        assertTrue(foundCartDetail.isPresent());
//        assertEquals(5, foundCartDetail.get().getQuantity());
//    }
//
//    @Test
//    void listCartDetails_Success() {
//        List<CartDetail> cartDetails = new ArrayList<>();
//        CartDetail cartDetail = new CartDetail();
//        cartDetail.setCart(cart);
//        cartDetails.add(cartDetail);
//
//        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
//        when(cartDetailRepository.findAll()).thenReturn(cartDetails);
//
//        List<CartDetail> details = cartService.listCartDetails(cart.getId());
//
//        assertFalse(details.isEmpty());
//        assertEquals(1, details.size());
//    }
//
//    @Test
//    void updateCartDetail_Success() {
//        CartDetail cartDetail = new CartDetail();
//        cartDetail.setId(1L);
//        cartDetail.setQuantity(5);
//
//        when(cartDetailRepository.findById(cartDetail.getId())).thenReturn(Optional.of(cartDetail));
//
//        CartDetail updatedDetail = cartService.updateCartDetail(cartDetail.getId(), 10);
//
//        assertEquals(10, updatedDetail.getQuantity());
//        verify(cartDetailRepository).save(cartDetail);
//    }
//
//    @Test
//    void deleteCartDetail_Success() {
//        Long detailId = 1L;
//
//        doNothing().when(cartDetailRepository).deleteById(detailId);
//        cartService.deleteCartDetail(detailId);
//
//        verify(cartDetailRepository).deleteById(detailId);
//    }
//}
//
