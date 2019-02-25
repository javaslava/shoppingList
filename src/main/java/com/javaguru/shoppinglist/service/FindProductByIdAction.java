package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.IdNotNullValidator;

import java.util.Scanner;

public class FindProductByIdAction implements Action {

    private static final String ACTION_NAME = "Find product by ID";

    private final ProductRepository productRepository;

    public FindProductByIdAction(ProductRepository repo) {
        this.productRepository = repo;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id: ");
        Long id = scanner.nextLong();
        try {
            new IdNotNullValidator().validate(id);
            Product response = productRepository.findBy(id);
            System.out.println("Response: " + response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
