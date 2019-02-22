package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartRepository;
import com.javaguru.shoppinglist.service.validation.CartValidationService;
import com.javaguru.shoppinglist.service.validation.SameCartNameValidator;

import java.util.Scanner;

public class CreateCartAction implements Action {

    private static final String CREATE_CART_ACTION = "Create Shopping Cart";

    private CartRepository cartRepository;
    private CartValidationService validationService = new CartValidationService();
    private SameCartNameValidator cartNameAnalyzer = new SameCartNameValidator();

    public CreateCartAction(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Shopping Cart name: ");
        String name = scanner.nextLine();
        ShoppingCart cart = new ShoppingCart();
        cart.setName(name);

        cartNameAnalyzer.checkForSameCartName(cart, cartRepository);
        validationService.validate(cart);
        cartRepository.create(cart.getName());
    }

    @Override
    public String toString() {
        return CREATE_CART_ACTION;
    }
}
