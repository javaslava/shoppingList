package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.repository.CartRepository;

public class CartRemoveContentManager implements CartManager {
    private final CartRepository shoppingCartRepo;

    public CartRemoveContentManager(CartRepository shoppingCartRepo) {
        this.shoppingCartRepo = shoppingCartRepo;
    }

    @Override
    public void manageCart(String cartName) {
        shoppingCartRepo.removeCartContent(cartName);
    }
}
