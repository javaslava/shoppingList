package com.javaguru.shoppinglist.config;

import com.javaguru.shoppinglist.console.*;
import com.javaguru.shoppinglist.service.CartManagerService;
import com.javaguru.shoppinglist.service.CartService;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "com.javaguru.shoppinglist")
public class ConfigConsoleAction {
    private ConfigProduct configProduct = new ConfigProduct();
    private ProductService productService = configProduct.getProductService();
    private ConfigCart configCart = new ConfigCart();
    private CartService cartService = configCart.getCartService();
    private ConfigCartManager configCartManager = new ConfigCartManager(productService, cartService);
    private CartManagerService cartManagerService = configCartManager.getCartManagerService();

    @Bean
    public Action getCreateProductAction() {
        return new CreateProductAction(productService);
    }

    @Bean
    public Action getFindProductByIdAction() {
        return new FindProductByIdAction(productService);
    }

    @Bean
    public Action getCreateCartAction() {
        return new CreateCartAction(cartService);
    }

    @Bean
    public Action getManageShoppingCartAction() {
        return new ManageShoppingCartAction(cartService, cartManagerService);
    }

    @Bean
    public Action getExitAction() {
        return new ExitAction();
    }

    @Bean
    public Scanner getScanner() {
        return new Scanner(System.in);
    }

}
