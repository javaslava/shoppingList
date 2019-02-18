package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.repository.CartRepository;

import java.util.Scanner;

public class CreateCartAction implements Action {

    private static final String ACTION_NAME = "Create Shopping Cart";

    private CartRepository cartRepository;

    public CreateCartAction(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Shopping Cart name: ");
        String name = scanner.nextLine();
    }


    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
