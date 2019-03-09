package com.javaguru.shoppinglist.config;

import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashSet;
import java.util.Set;

@Configuration
class ConfigProduct {

    @Bean
    ProductRepository getProductRepository() {
        return new ProductRepository();
    }

    @Bean
    ProductValidationRule getProductNameValidationRule() {
        return new ProductNameNotNullValidator();
    }

    @Bean
    ProductValidationRule getProductNameLengthValidationRule() {
        return new ProductNameLengthValidator();
    }

    @Bean
    ProductValidationRule getProductUniqueNameValidationRule() {
        return new ProductUniqueNameValidator(getProductRepository());
    }

    @Bean
    ProductValidationRule getProductPriceValidationRule() {
        return new ProductPriceValidator();
    }

    @Bean
    ProductValidationRule getProductDiscountValidationRule() {
        return new ProductDiscountValidator();
    }

    @Bean
    ProductValidationRule getProductDescriptionValidationRule() {
        return new ProductDescriptionValidator();
    }

    @Bean
    ProductValidationRule getProductCategoryValidationRule() {
        return new ProductCategoryNameValidator();
    }

    @Bean
    Set<ProductValidationRule> getProductRules() {
        Set<ProductValidationRule> productRules = new LinkedHashSet<>();
        productRules.add(getProductNameValidationRule());
        productRules.add(getProductNameLengthValidationRule());
        productRules.add(getProductUniqueNameValidationRule());
        productRules.add(getProductPriceValidationRule());
        productRules.add(getProductDiscountValidationRule());
        productRules.add(getProductDescriptionValidationRule());
        productRules.add(getProductCategoryValidationRule());
        return productRules;
    }

    @Bean
    ProductValidationService getProductValidationService() {
        return new ProductValidationService(getProductRules());
    }

    @Bean
    ProductService getProductService() {
        return new ProductService(getProductRepository(), getProductValidationService());
    }
}
