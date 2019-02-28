package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.repository.CartManagerRepository;
import com.javaguru.shoppinglist.repository.CartRepository;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.CartManagerValidator;
import com.javaguru.shoppinglist.service.validation.CartRepositorySizeValidator;

import java.util.*;

public class ManageShoppingCartAction implements Action {
    private static final String MANAGE_CART_ACTION = "Manage shopping cart";
    private CartRepository shoppingCartRepository;
    private ProductRepository productRepository;

    public ManageShoppingCartAction(CartRepository cartRepository, ProductRepository productRepository) {
        this.shoppingCartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void execute() {
        CartManagerRepository cartManager = new CartManagerRepository(productRepository, shoppingCartRepository);
        String cartName = chooseCartByName();
        int response = 1;
        while (response > 0 && response < cartManager.getCartManagerSize()) {
            cartManager.printCartManagerMenu(cartName);
            response = userNumberInput();
            new CartManagerValidator(cartManager).validate(response);
            cartManager.runCartManagerMenuChoice(response, cartName);
        }
    }

    private String chooseCartByName() {
        String cartName;
        do {new CartRepositorySizeValidator().validate(shoppingCartRepository.getShoppingCartRepoSize());
            System.out.println("Choose shopping cart to manage: " + shoppingCartRepository.getShoppingCartsNames());
            cartName = userStringInput();
        } while (!shoppingCartRepository.checkForCartByName(cartName));
        return cartName;
    }

    private String userStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private int userNumberInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    @Override
    public String toString() {
        return MANAGE_CART_ACTION;
    }
}
