package com.javaguru.shoppinglist.console.cartManager;

import com.javaguru.shoppinglist.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartDeleteProductManager implements CartManager {

    private final CartService cartService;

    @Autowired
    public CartDeleteProductManager(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void manageCart(String cartName) {
        System.out.println("Enter product name to remove from " + cartName);
        cartService.deleteProductFromCart(cartName, userStringInput());
    }
}
