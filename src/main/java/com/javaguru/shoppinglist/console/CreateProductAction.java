package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.service.ProductService;

import java.util.Scanner;

public class CreateProductAction implements Action {
    private static final String ACTION_NAME = "Create Product";

    private final ProductService productService;

    public CreateProductAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
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
