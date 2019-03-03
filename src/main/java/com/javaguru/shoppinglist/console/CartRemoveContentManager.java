package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.service.CartService;

public class CartRemoveContentManager implements CartManager {

    private final CartService cartService;

    public CartRemoveContentManager(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void manageCart(String cartName) {
        cartService.removeCartContent(cartName);
    }
}
