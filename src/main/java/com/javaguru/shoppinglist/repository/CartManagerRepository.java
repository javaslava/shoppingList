package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.console.*;

import java.util.Map;

public class CartManagerRepository {

    private Map<String, CartManager> cartManager;

    public CartManagerRepository(Map<String, CartManager> cartManager) {
        this.cartManager = cartManager;
    }

    public int getCartManagerSize() {
        return cartManager.size();
    }

    public void printCartManagerMenu(String cartName) {
        cartManager.keySet().stream().forEach(element -> System.out.println(element + " '" + cartName + "'"));
    }

    public void runCartManagerMenuChoice(int response, String cartName) {
        cartManager.values().stream().skip(response - 1).findFirst().get().manageCart(cartName);
    }
}
