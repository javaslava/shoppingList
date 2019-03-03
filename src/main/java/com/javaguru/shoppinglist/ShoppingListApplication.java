package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.console.ConsoleUI;
import com.javaguru.shoppinglist.repository.CartRepository;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.CartService;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.*;

import java.util.LinkedHashSet;
import java.util.Set;

class ShoppingListApplication {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        CartRepository cartRepository = new CartRepository();

        ProductValidationRule productNameValidationRule = new ProductNameNotNullValidator();
        ProductValidationRule productNameLengthValidationRule = new ProductNameLengthValidator();
        ProductValidationRule productUniqueNameValidationRule = new ProductUniqueNameValidator(productRepository);
        ProductValidationRule productPriceValidationRule = new ProductPriceValidator();
        ProductValidationRule productDiscountValidationRule = new ProductDiscountValidator();
        ProductValidationRule productDescriptionValidationRule = new ProductDescriptionValidator();
        ProductValidationRule productCategoryValidationRule = new ProductCategoryNameValidator();

        Set<ProductValidationRule> productRules = new LinkedHashSet<>();
        productRules.add(productNameValidationRule);
        productRules.add(productNameLengthValidationRule);
        productRules.add(productUniqueNameValidationRule);
        productRules.add(productPriceValidationRule);
        productRules.add(productDiscountValidationRule);
        productRules.add(productDescriptionValidationRule);
        productRules.add(productCategoryValidationRule);

        ProductValidationService productValidationService = new ProductValidationService(productRules);
        ProductService productService = new ProductService(productRepository, productValidationService);


        CartValidationRule cartNameLengthValidationRule = new CartNameLengthValidator();
        CartValidationRule cartUniqueNameValidationRule = new CartUniqueNameValidator(cartRepository);
        Set<CartValidationRule> cartRules = new LinkedHashSet<>();
        cartRules.add(cartNameLengthValidationRule);
        cartRules.add(cartUniqueNameValidationRule);

        CartValidationService cartValidationService = new CartValidationService(cartRules);
        CartService cartService = new CartService(cartRepository, cartValidationService);

        ConsoleUI ui = new ConsoleUI(productService, cartService);
        ui.start();
    }
}
