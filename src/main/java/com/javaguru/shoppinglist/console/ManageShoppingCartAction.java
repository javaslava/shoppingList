package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.service.CartManagerService;
import com.javaguru.shoppinglist.service.CartService;

import java.util.*;

public class ManageShoppingCartAction implements Action {
    private static final String MANAGE_CART_ACTION = "Manage shopping cart";
    private final CartService cartService;
    private final CartManagerService managerService;

    public ManageShoppingCartAction(CartService cartService, CartManagerService cartManagerService) {
        this.cartService = cartService;
        this.managerService = cartManagerService;
    }

    @Override
    public void execute() {
        String cartName = chooseCartByName();
        int response = 1;
        while (response > 0 && response < managerService.getCartManagerSize()) {
            managerService.printCartManagerMenu(cartName);
            response = userNumberInput();
            managerService.runCartManagerMenuChoice(response, cartName);
        }
    }

    private String chooseCartByName() {
        String cartName;
        do {
            cartService.checkCartRepoNotEmpty();
            System.out.println("Choose shopping cart to manage: " + cartService.getShoppingCartsNames());
            cartName = userStringInput();
        } while (!cartService.checkForCartByName(cartName));
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
