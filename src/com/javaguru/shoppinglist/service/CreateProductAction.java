package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.*;

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
        Product product = new Product();
        assignProductFields(product);

        try {
            new ProductNotNullValidator().validate(product);
            Long response = productService.create(product);
            System.out.println("Response: " + response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void assignProductFields(Product product) {
        String[] productFieldsArray = {"name", "price", "discount", "description", "category"};
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < productFieldsArray.length; i++) {

            if (product.getPrice().compareTo(new BigDecimal("20")) == -1) {
                if (i == 2) {
                    continue;
                }
            }

            System.out.println("Enter product's " + productFieldsArray[i] + ":");
            boolean fieldAssigned = false;

            while (!fieldAssigned) {
                String userInput = scanner.nextLine();
                try {
                    chooseProductFieldToFill(product, productFieldsArray[i], userInput);
                    fieldAssigned = true;
                } catch (ValidationException ex) {
                    System.err.println(ex.getMessage());
                    System.out.println("Please, enter correct product's " + productFieldsArray[i] + ":");
                }
            }

        }
    }

    private void chooseProductFieldToFill(Product product, String productField, String userInput) {
        switch (productField) {
            case "name":
                setNameField(product, userInput);
                break;
            case "price":
                setPriceField(product, userInput);
                break;
            case "discount":
                setDiscountField(product, userInput);
                break;
            case "description":
                setDescriptionField(product, userInput);
                break;
            case "category":
                setCategoryField(product, userInput);
                break;
        }
    }

    private void setNameField(Product product, String name) {

        new SameProductNameValidator().validate(productService.getDatabaseValues(), name);
        new NameLengthValidator().validate(name);
        product.setName(name);
    }

    private void setPriceField(Product product, String price) {
        if (price.equals("")) {
            price = "0";
        }
        if (price.contains(",")) {
            price = price.replace(',', '.');
        }
        new PriceValidator().validate(new BigDecimal(price));
        product.setPrice(new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    private void setDiscountField(Product product, String discount) {
        if (discount.equals("")) {
            discount = "0";
        }
        if (discount.contains(",")) {
            discount = discount.replace(',', '.');
        }
        new DiscountValidator().validate(new BigDecimal(discount));
        product.setDiscount(new BigDecimal(discount).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    private void setDescriptionField(Product product, String description) {
        if (description.equals("")) {
            description = "NO DESCRIPTION";
        }
        new DescriptionValidator().validate(description);
        product.setDescription(description);
    }

    private void setCategoryField(Product product, String category) {
        category = category.toUpperCase();
        new CategoryValidator().validate(category);
        Category productCategory = Category.valueOf(category);
        product.setCategory(productCategory);
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
