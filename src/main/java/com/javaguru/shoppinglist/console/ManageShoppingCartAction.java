package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.repository.CartManagerRepository;
import com.javaguru.shoppinglist.service.CartService;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.CartManagerValidator;

import java.util.*;

public class ManageShoppingCartAction implements Action {
    private static final String MANAGE_CART_ACTION = "Manage shopping cart";

    private final CartService cartService;
    private final ProductService productService;

    public ManageShoppingCartAction(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @Override
    public void execute() {
        CartManagerRepository cartManager = new CartManagerRepository(productService, cartService);
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
