package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.repository.CartRepository;

public class CartRemoveContentManager implements CartManager {

    @Override
    public void manageCart(String cartName, CartRepository shoppingCartRepository) {
        shoppingCartRepository.removeCartContent(cartName);
    }
}
