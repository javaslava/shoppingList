package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.service.CartService;

public class CartPrintContentManager implements CartManager {

    private final CartService cartService;

    public CartPrintContentManager(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void manageCart(String cartName) {
        cartService.printCartContent(cartName);
    }
}
