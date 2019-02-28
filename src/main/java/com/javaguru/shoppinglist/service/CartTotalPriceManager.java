package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.repository.CartRepository;

import java.math.BigDecimal;

public class CartTotalPriceManager implements CartManager {
    private final CartRepository shoppingCartRepo;

    public CartTotalPriceManager(CartRepository shoppingCartRepo) {
        this.shoppingCartRepo = shoppingCartRepo;
    }

    @Override
//    public void manageCart(String cartName, CartRepository shoppingCartRepository) {
//        BigDecimal totalCartContentPrice = shoppingCartRepository.getTotalCartPrice(cartName);
//        System.out.println("Total actual price of " + cartName + " content: " + totalCartContentPrice);
//    }

    public void manageCart(String cartName) {
        BigDecimal totalCartContentPrice = shoppingCartRepo.getTotalCartPrice(cartName);
        System.out.println("Total actual price of " + cartName + " content: " + totalCartContentPrice);
    }
}
