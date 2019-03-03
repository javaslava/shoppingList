package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.repository.ConsoleUIMenuRepository;
import com.javaguru.shoppinglist.service.*;

import java.util.Scanner;

public class ConsoleUI {

    private final ProductService productService;
    private final CartService cartService;

    public ConsoleUI(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    public void start() {
        ConsoleUIMenuRepository consoleMenu = new ConsoleUIMenuRepository(productService, cartService);
        Scanner scanner = new Scanner(System.in);
        int response = 0;
        while (response >= 0) {
            printMenu(consoleMenu);
            try {
                response = scanner.nextInt() - 1;
                consoleMenu.chooseAction(response).execute();
            } catch (Exception e) {
                if (e.getMessage() == null) {
                    System.out.println("Error! Please try again.");
                } else {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void printMenu(ConsoleUIMenuRepository consoleMenu) {
        for (int i = 1; i <= consoleMenu.getConsoleMenuSize(); i++) {
            System.out.println(i + ". " + consoleMenu.getActionName(i - 1));
        }
    }
}
