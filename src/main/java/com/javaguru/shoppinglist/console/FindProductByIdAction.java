package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.domain.Product;

import com.javaguru.shoppinglist.service.ProductService;

import java.util.Scanner;

public class FindProductByIdAction implements Action {
    private final Scanner scanner = new Scanner(System.in);
    private static final String ACTION_NAME = "Find product by ID";
    private final ProductService productService;

    public FindProductByIdAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        System.out.println("Enter product id: ");
        Long id = scanner.nextLong();
        Product response = productService.findProductById(id);
        System.out.println("Response: " + response);
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
