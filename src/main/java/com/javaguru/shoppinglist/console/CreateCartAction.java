package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.service.CartService;

import java.util.Scanner;

public class CreateCartAction implements Action {
    private final Scanner scanner = new Scanner(System.in);
    private static final String CREATE_CART_ACTION = "Create Shopping Cart";
    private final CartService cartService;

    public CreateCartAction(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        System.out.println("Enter Shopping Cart name: ");
        String name = scanner.nextLine();
        String cartName = cartService.createCart(name);
        System.out.println("Created cart name: " + cartName);
    }

    @Override
    public String toString() {
        return CREATE_CART_ACTION;
    }
}
