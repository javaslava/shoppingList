package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.CartRepository;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductNotNullValidator;

import java.math.BigDecimal;
import java.util.Scanner;

public class ManageShoppingCartAction implements Action {
    private static final String ACTION_NAME = "Manage shopping cart";
    private CartRepository shoppingCartRepository;
    private ProductRepository productRepository;

    public ManageShoppingCartAction(CartRepository cartRepository, ProductRepository productRepository) {
        this.shoppingCartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void execute() {
        String cartName;
        do {
            System.out.println("Choose shopping cart to manage: " + shoppingCartRepository.getShoppingCartsNames());
            cartName = userStringInput();
        } while (!shoppingCartRepository.checkForCartByName(cartName));


        int response = 1;
        while (response > 0 && response < 6) {
            System.out.println("1. Add product to shopping cart");
            System.out.println("2. Delete product from shopping cart");
            System.out.println("3. Get a list of products in the cart");
            System.out.println("4. Get total actual price of products in the cart");
            System.out.println("5. Remove all cart content");
            System.out.println("6. Delete shopping cart");
            System.out.println("7. Exit to main menu");
            response = userNumberInput();

            switch (response) {
                case 1:
                    System.out.println("Enter product name to add it to cart:");
                    Product productToAdd = productRepository.getProductByName(userStringInput());
                    new ProductNotNullValidator().validate(productToAdd);
                    shoppingCartRepository.addProductToCart(cartName, productToAdd);
                    break;
                case 2:
                    System.out.println("Enter product name to remove it from cart:");
                    Product productToDelete = shoppingCartRepository.getProductByName(cartName, userStringInput());
                    new ProductNotNullValidator().validate(productToDelete);
                    shoppingCartRepository.deleteProductFromCart(cartName, productToDelete);
                    break;
                case 3:
                    shoppingCartRepository.printCartContent(cartName);
                    break;
                case 4:
                    BigDecimal totalCartContentPrice = shoppingCartRepository.getTotalCartPrice(cartName);
                    System.out.println("Total actual price of cart content: " + totalCartContentPrice);
                    break;
                case 5:
                    shoppingCartRepository.removeCartContent(cartName);
                    break;
                case 6:
                    shoppingCartRepository.deleteCartByName(cartName);
                    break;
                case 7:
                    break;
            }
        }
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
        return ACTION_NAME;
    }
}
