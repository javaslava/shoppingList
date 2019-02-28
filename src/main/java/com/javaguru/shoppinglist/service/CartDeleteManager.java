package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.repository.CartRepository;

public class CartDeleteManager implements CartManager {
    private final CartRepository shoppingCartRepo;

    public CartDeleteManager(CartRepository shoppingCartRepo) {
        this.shoppingCartRepo = shoppingCartRepo;
    }

    @Override
    public void manageCart(String cartName) {
        shoppingCartRepo.deleteCartByName(cartName);
    }
}
