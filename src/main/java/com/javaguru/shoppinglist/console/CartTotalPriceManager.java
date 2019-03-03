package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.service.CartService;

import java.math.BigDecimal;

public class CartTotalPriceManager implements CartManager {

    private final CartService cartService;

    public CartTotalPriceManager(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void manageCart(String cartName) {
        BigDecimal totalCartContentPrice = cartService.getTotalCartPrice(cartName);
        System.out.println("Total actual price of " + cartName + " content: " + totalCartContentPrice);
    }
}
