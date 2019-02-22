package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.repository.CartRepository;

import java.math.BigDecimal;

public class CartTotalPriceManager implements CartManager {

    @Override
    public void manageCart(String cartName, CartRepository shoppingCartRepository) {
        BigDecimal totalCartContentPrice = shoppingCartRepository.getTotalCartPrice(cartName);
        System.out.println("Total actual price of " + cartName + " content: " + totalCartContentPrice);
    }
}
