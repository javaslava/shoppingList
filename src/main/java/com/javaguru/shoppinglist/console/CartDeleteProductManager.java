package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.service.CartService;

public class CartDeleteProductManager implements CartManager {

    private final CartService cartService;

    public CartDeleteProductManager(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void manageCart(String cartName) {
        System.out.println("Enter product name to remove from " + cartName);
        cartService.deleteProductFromCart(cartName, userStringInput());
    }
}
