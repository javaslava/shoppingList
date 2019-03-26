package com.javaguru.shoppinglist.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleUI {
    private final ConsoleUIMenu consoleMenu;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public ConsoleUI(ConsoleUIMenu consoleMenu) {
        this.consoleMenu = consoleMenu;
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

    private void printMenu(ConsoleUIMenu consoleMenu) {
        for (int i = 1; i <= consoleMenu.getConsoleMenuSize(); i++) {
            System.out.println(i + ". " + consoleMenu.getActionName(i - 1));
        }
    }
}
