package com.javaguru.shoppinglist.config;

import com.javaguru.shoppinglist.repository.CartRepository;
import com.javaguru.shoppinglist.service.CartService;
import com.javaguru.shoppinglist.service.validation.CartNameLengthValidator;
import com.javaguru.shoppinglist.service.validation.CartUniqueNameValidator;
import com.javaguru.shoppinglist.service.validation.CartValidationRule;
import com.javaguru.shoppinglist.service.validation.CartValidationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashSet;
import java.util.Set;

@Configuration
class ConfigCart {

    @Bean
    CartRepository getCartRepository() {
        return new CartRepository();
    }

    @Bean
    CartValidationRule getCartNameLengthValidationRule() {
        return new CartNameLengthValidator();
    }

    @Bean
    CartValidationRule getCartUniqueNameValidationRule() {
        return new CartUniqueNameValidator(getCartRepository());
    }

    @Bean
    Set<CartValidationRule> getCartRules() {
        Set<CartValidationRule> cartRules = new LinkedHashSet<>();
        cartRules.add(getCartNameLengthValidationRule());
        cartRules.add(getCartUniqueNameValidationRule());
        return cartRules;
    }

    @Bean
    CartValidationService getCartValidationService() {
        return new CartValidationService(getCartRules());
    }

    @Bean
    CartService getCartService() {
        return new CartService(getCartRepository(), getCartValidationService());
    }
}
