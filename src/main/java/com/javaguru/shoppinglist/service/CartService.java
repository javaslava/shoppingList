package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartRepository;
import com.javaguru.shoppinglist.service.validation.CartRepositorySizeValidator;
import com.javaguru.shoppinglist.service.validation.CartValidationService;

import java.math.BigDecimal;
import java.util.Optional;

public class CartService {

    private final CartRepository cartRepo;
    private final CartValidationService validationService;

    public CartService(CartRepository cartRepo, CartValidationService validationService) {
        this.cartRepo = cartRepo;
        this.validationService = validationService;
    }

    public String createCart(String name) {
        ShoppingCart cart = new ShoppingCart();
        cart.setName(name);
        validationService.validate(cart);
        ShoppingCart createdCart = cartRepo.insert(cart);
        return createdCart.getName();
    }

    public void deleteCartByName(String cartName) {
        cartRepo.deleteCartByName(cartName);
    }

    public void removeCartContent(String cartName) {
        cartRepo.removeCartContent(cartName);
    }

    public BigDecimal getTotalCartPrice(String cartName) {
        return cartRepo.getTotalCartPrice(cartName);
    }

    public void printCartContent(String cartName) {
        cartRepo.printCartContent(cartName);
    }

    public void deleteProductFromCart(String cartName, String productNameToDelete) {
        Optional<Product> productToDelete = cartRepo.getProductByName(cartName, productNameToDelete);
        productToDelete.orElseThrow(() -> new IllegalArgumentException("Product named " + productNameToDelete + " not" +
                " found"));
        cartRepo.deleteProductFromCart(cartName, productToDelete.get());
    }

    public void addProductToCart(String cartName, Product product) {
        cartRepo.addProductToCart(cartName, product);
    }

    public String getShoppingCartsNames() {
        return cartRepo.getShoppingCartsNames();
    }

    public boolean checkForCartByName(String cartName) {
        return cartRepo.checkForCartByName(cartName);
    }

    public void checkCartRepoNotEmpty() {
        new CartRepositorySizeValidator().validate(cartRepo.getShoppingCartRepoSize());
    }
}
