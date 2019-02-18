package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.repository.CartRepository;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private ProductRepository productRepository = new ProductRepository();
    private CartRepository shoppingCartRepository = new CartRepository();

    private Action exitAction = new ExitAction();
    private Action createProductUserAction = new CreateProductAction(productRepository);
    private Action findByIdUserAction = new FindProductByIdAction(productRepository);
    private Action createShoppingCartUserAction = new CreateCartAction(shoppingCartRepository);
    private List<Action> actions = new ArrayList<>();

    public void start() {
        actions.add(createProductUserAction);
        actions.add(findByIdUserAction);
        actions.add(createShoppingCartUserAction);
        actions.add(exitAction);
        Scanner scanner = new Scanner(System.in);
        int response = 0;
        while (response >= 0) {
            printMenu();
            try {
                response = scanner.nextInt() - 1;
                actions.get(response).execute();
            } catch (Exception e) {
                if (e.getMessage() == null) {
                    System.out.println("Error! Please try again.");
                } else {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void printMenu() {
        for (int i = 1; i <= actions.size(); i++) {
            System.out.println(i  + ". " + actions.get(i-1));
        }
    }
}
