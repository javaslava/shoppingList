package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CreateProductAction implements Action {
    private final Scanner scanner = new Scanner(System.in);
    private static final String ACTION_NAME = "Create Product";
    private final ProductService productService;

    public CreateProductAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter product price: ");
        String price = scanner.nextLine();
        System.out.println("Enter product description: ");
        String description = scanner.nextLine();
        System.out.println("Enter product discount: ");
        String discount = scanner.nextLine();
        System.out.println("Enter product category: ");
        String category = scanner.nextLine().toUpperCase();
        Long id = productService.createProduct(name, price, description, discount, category);
        System.out.println("Created product ID: " + id);
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
