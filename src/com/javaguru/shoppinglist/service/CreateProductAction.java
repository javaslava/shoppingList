package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.converting.ActualPriceConverter;
import com.javaguru.shoppinglist.service.converting.DescriptionConverter;
import com.javaguru.shoppinglist.service.converting.DiscountConverter;
import com.javaguru.shoppinglist.service.converting.PriceConverter;
import com.javaguru.shoppinglist.service.validation.*;

import java.math.BigDecimal;
import java.util.Scanner;

public class CreateProductAction implements Action {
    private static final String ACTION_NAME = "Create Product";
    private ProductRepository productRepository;
    private ProductValidationService validationService = new ProductValidationService();
    private SameProductNameValidator productNameAnalyzer = new SameProductNameValidator();

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
        BigDecimal priceValue = new PriceConverter().priceFilter(price);
        product.setPrice(priceValue);
        description = new DescriptionConverter().descriptionFilter(description);
        product.setDescription(description);
        BigDecimal discountValue = new DiscountConverter().discountFilter(discount, product.getPrice());
        product.setDiscount(discountValue);
        BigDecimal actualPrice = new ActualPriceConverter().actualPriceCalculator(product.getPrice(), product.getDiscount());
        product.setActualPrice(actualPrice);
        product.setCategory(category);

        productNameAnalyzer.checkForSameProductName(product, productRepository);
        validationService.validate(product);
        Long createdProductID = productRepository.create(product);
        System.out.println("Created product ID: " + createdProductID);
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
