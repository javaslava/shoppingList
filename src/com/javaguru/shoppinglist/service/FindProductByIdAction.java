package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.IdNotNullValidator;

import java.util.Scanner;

public class FindProductByIdAction implements Action {

    private static final String ACTION_NAME = "Find by ID";

    private final ProductService productService;

    public FindProductByIdAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id: ");
        Long id = scanner.nextLong();
        try{
            new IdNotNullValidator().validate(id);
            Product response = productService.findBy(id);
            System.out.println("Response: " + response);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
