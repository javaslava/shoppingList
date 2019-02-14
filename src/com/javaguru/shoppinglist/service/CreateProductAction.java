package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.*;

import java.util.Scanner;

public class CreateProductAction implements Action {
    private static final String ACTION_NAME = "Create Product";
    private ProductRepository productRepository;
    private ProductValidationService validationService = new ProductValidationService();

    public CreateProductAction(ProductRepository repo) {
        this.productRepository = repo;
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

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setDiscount(discount);
        product.setCategory(category);

        validationService.validate(product, productRepository);
        Long createdProductID = productRepository.create(product);
        System.out.println("Created product ID: " + createdProductID);
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
