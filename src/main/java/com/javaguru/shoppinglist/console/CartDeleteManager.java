package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.service.CartService;

public class CartDeleteManager implements CartManager {

    private final CartService cartService;

    public CartDeleteManager(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void manageCart(String cartName) {
        cartService.deleteCartByName(cartName);
    }
}
