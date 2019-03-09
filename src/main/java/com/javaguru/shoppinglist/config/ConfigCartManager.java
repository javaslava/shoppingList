package com.javaguru.shoppinglist.config;

import com.javaguru.shoppinglist.console.*;
import com.javaguru.shoppinglist.repository.CartManagerRepository;
import com.javaguru.shoppinglist.service.CartManagerService;
import com.javaguru.shoppinglist.service.CartService;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.CartManagerValidationRule;
import com.javaguru.shoppinglist.service.validation.CartManagerValidationService;
import com.javaguru.shoppinglist.service.validation.CartManagerValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Configuration
class ConfigCartManager {

    private ProductService productService;
    private CartService cartService;

    ConfigCartManager(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @Bean
    CartManager getCartAddProductManager() {
        return new CartAddProductManager(productService, cartService);
    }

    @Bean
    CartManager getCartDeleteProductManager() {
        return new CartDeleteProductManager(cartService);
    }

    @Bean
    CartManager getCartPrintContentManager() {
        return new CartPrintContentManager(cartService);
    }

    @Bean
    CartManager getCartTotalPriceManager() {
        return new CartTotalPriceManager(cartService);
    }

    @Bean
    CartManager getCartRemoveContentManager() {
        return new CartRemoveContentManager(cartService);
    }

    @Bean
    CartManager getCartDeleteManager() {
        return new CartDeleteManager(cartService);
    }

    @Bean
    Map<String, CartManager> getCartManagerMenu() {
        Map<String, CartManager> cartManagerMenu = new LinkedHashMap<>();
        cartManagerMenu.put("1. Add product to ", getCartAddProductManager());
        cartManagerMenu.put("2. Delete product from ", getCartDeleteProductManager());
        cartManagerMenu.put("3. Get list of products in ", getCartPrintContentManager());
        cartManagerMenu.put("4. Get total actual price of products in ", getCartTotalPriceManager());
        cartManagerMenu.put("5. Remove all from ", getCartRemoveContentManager());
        cartManagerMenu.put("6. Delete ", getCartDeleteManager());
        return cartManagerMenu;
    }

    @Bean
    CartManagerRepository getCartManager() {
        return new CartManagerRepository(getCartManagerMenu());
    }

    @Bean
    CartManagerValidationRule getCartManagerValidationRule() {
        return new CartManagerValidator(getCartManager());
    }

    @Bean
    Set<CartManagerValidationRule> getManagerValidationRules() {
        Set<CartManagerValidationRule> managerValidationRules = new LinkedHashSet<>();
        managerValidationRules.add(getCartManagerValidationRule());
        return managerValidationRules;
    }

    @Bean
    CartManagerValidationService getManagerValidationService() {
        return new CartManagerValidationService(getManagerValidationRules());
    }

    @Bean
    CartManagerService getCartManagerService() {
        return new CartManagerService(getCartManager(), getManagerValidationService());
    }
}
