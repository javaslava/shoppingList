package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.repository.CartRepository;

public class CartPrintContentManager implements CartManager {

    private final CartRepository shoppingCartRepo;

    public CartPrintContentManager(CartRepository shoppingCartRepo) {
        this.shoppingCartRepo = shoppingCartRepo;
    }

    @Override
    public void manageCart(String cartName) {
        shoppingCartRepo.printCartContent(cartName);
    }
}
