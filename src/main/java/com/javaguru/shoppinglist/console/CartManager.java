package com.javaguru.shoppinglist.console;

import java.util.Scanner;

public interface CartManager {
    void manageCart(String cartName);

    default String userStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
