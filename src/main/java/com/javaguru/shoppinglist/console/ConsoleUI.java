package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.repository.ConsoleUIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleUI {
    private final ConsoleUIRepository consoleMenu;
    private final Scanner scanner;

    @Autowired
    public ConsoleUI(ConsoleUIRepository consoleMenu, Scanner scanner) {
        this.consoleMenu = consoleMenu;
        this.scanner = scanner;
    }

    public void start() {
        int response = 0;
        while (response >= 0) {
            printMenu(consoleMenu);
            try {
                response = scanner.nextInt() - 1;
                consoleMenu.getActionName(response).execute();
            } catch (Exception e) {
                if (e.getMessage() == null) {
                    System.out.println("Error! Please try again.");
                } else {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void printMenu(ConsoleUIRepository consoleMenu) {
        for (int i = 1; i <= consoleMenu.getConsoleMenuSize(); i++) {
            System.out.println(i + ". " + consoleMenu.getActionName(i - 1));
        }
    }
}
