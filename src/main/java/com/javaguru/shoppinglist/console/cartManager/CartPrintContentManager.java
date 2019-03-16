package com.javaguru.shoppinglist.console.cartManager;

import com.javaguru.shoppinglist.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartPrintContentManager implements CartManager {

    private final CartService cartService;

    @Autowired
    public CartPrintContentManager(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void manageCart(String cartName) {
        cartService.printCartContent(cartName);
    }
}
