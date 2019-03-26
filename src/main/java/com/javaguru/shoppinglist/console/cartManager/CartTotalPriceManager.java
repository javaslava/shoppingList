package com.javaguru.shoppinglist.console.cartManager;

import com.javaguru.shoppinglist.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CartTotalPriceManager implements CartManager {

    private final CartService cartService;

    @Autowired
    public CartTotalPriceManager(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void manageCart(String cartName) {
        BigDecimal totalCartContentPrice = cartService.getTotalCartPrice(cartName);
        System.out.println("Total actual price of " + cartName + " content: " + totalCartContentPrice);
    }
}
