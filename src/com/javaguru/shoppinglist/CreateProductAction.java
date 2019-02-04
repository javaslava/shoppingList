package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.validators.DiscountValidator;
import com.javaguru.shoppinglist.validators.NameLengthValidator;
import com.javaguru.shoppinglist.validators.PriceValidator;
import com.javaguru.shoppinglist.validators.ValidationException;

import java.math.BigDecimal;
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
        Product product = new Product();

        System.out.println("Enter product name:");
        String name = scanner.nextLine();

        try {
            new NameLengthValidator().validate(name);
            product.setName(name);

            isPrice(product, scanner);
            isDiscount(product, scanner);

        } catch (ValidationException ex) {
            System.err.println(ex.getMessage());
        }

        try {
            Long response = productService.create(product);
            System.out.println("Response: " + response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void isPrice(Product product, Scanner scanner) {
        System.out.println("Enter product price: ");
        boolean isPrice = false;
        while (!isPrice) {
            String price = scanner.nextLine();
            try {
                new PriceValidator().validate(new BigDecimal(price));
                product.setPrice(new BigDecimal(price));
                isPrice = true;
            } catch (ValidationException ex) {
                System.err.println(ex.getMessage());
                System.out.println("Please, enter correct product price: ");
            }
        }
    }

    private static void isDiscount(Product product, Scanner scanner) {
        System.out.println("If product has discount, please enter it: ");
        boolean isDiscount = false;
        while (!isDiscount) {
            String discount = scanner.nextLine();
            if (discount == null) {
                discount = "0";
            }
            try {
                new DiscountValidator().validate(new BigDecimal(discount));
                product.setDiscount(new BigDecimal(discount));
                isDiscount = true;
            } catch (ValidationException ex) {
                System.err.println(ex.getMessage());
                System.out.println("Please, enter correct product discount: ");
            }
        }
    }


    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
