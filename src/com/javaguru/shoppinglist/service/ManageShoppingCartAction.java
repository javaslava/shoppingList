package com.javaguru.shoppinglist.service;

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

    private Map<String, CartManager> cartManager = new LinkedHashMap<>();

    @Override
    public void execute() {
        cartManager.put("1. Add product to ", new CartAddProductManager(productRepository));
        cartManager.put("2. Delete product from ", new CartDeleteProductManager());
        cartManager.put("3. Get list of products in ", new CartPrintContentManager());
        cartManager.put("4. Get total actual price of products in ", new CartTotalPriceManager());
        cartManager.put("5. Remove all from ", new CartRemoveContentManager());
        cartManager.put("6. Delete ", new CartDeleteManager());

        String cartName = chooseCartByName();

        int response = 1;
        while (response > 0 && response < cartManager.size()) {
            cartManager.keySet().stream().forEach(element -> System.out.println(element + " '" + cartName + "'"));
            response = userNumberInput();
            new CartManagerValidator().validate(cartManager, response);
            cartManager.values().stream().skip(response - 1).findFirst().get().manageCart(cartName,
                    shoppingCartRepository);
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
