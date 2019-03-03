package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartRepository;
import com.javaguru.shoppinglist.service.validation.CartValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
    @Mock
    private CartRepository cartRepo;
    @Mock
    private CartValidationService validationService;
    @InjectMocks
    private CartService victim;

    @Test
    public void shouldCreateCart() {
        ShoppingCart shoppingCart = createCart();
        when(cartRepo.insert(shoppingCart)).thenReturn(shoppingCart);
        String cartName = "TEST_CART";
        String createdCartName = victim.createCart(cartName);
        assertEquals("TEST_CART", createdCartName);
    }

    @Test
    public void shouldTestGetTotalCartPrice(){
        BigDecimal price = new BigDecimal("10.5");
        when(cartRepo.getTotalCartPrice(createCart().getName())).thenReturn(price);
        BigDecimal returnedPriceValue = victim.getTotalCartPrice(createCart().getName());
        assertEquals(new BigDecimal(10.5), returnedPriceValue);
    }

    @Test
    public void shouldTestGetShoppingCartsNames(){
        String cartName = "TEST_CART";
        when(cartRepo.getShoppingCartsNames()).thenReturn(createCart().getName());
        String returnedCartNames = victim.getShoppingCartsNames();
        assertEquals(cartName, returnedCartNames);
    }

    @Test
    public void shouldTestCheckForCartByName(){
        String cartName = "TEST_CART";
        when(cartRepo.checkForCartByName(cartName)).thenReturn(true);
        boolean returnedBoolean = victim.checkForCartByName(cartName);
        assertTrue(returnedBoolean);
    }

    private ShoppingCart createCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.setName("TEST_CART");
        return cart;
    }
}