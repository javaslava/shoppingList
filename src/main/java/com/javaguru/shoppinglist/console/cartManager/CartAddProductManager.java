package com.javaguru.shoppinglist.console.cartManager;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.CartService;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CartAddProductManager implements CartManager {

    private final CartService cartService;
    private final ProductService productService;

    @Autowired
    public CartAddProductManager(ProductService productService, CartService cartService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @Override
    public void manageCart(String cartName) {
        System.out.println("Enter product name to add to " + cartName);
        Optional<Product> productToAdd = productService.getProductByName(userStringInput());
        cartService.addProductToCart(cartName, productToAdd);
    }
}
