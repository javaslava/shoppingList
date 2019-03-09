package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.console.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ShoppingListApplication {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext("com.javaguru.shoppinglist.config");
        ConsoleUI console = context.getBean(ConsoleUI.class);
        console.start();
    }
}
