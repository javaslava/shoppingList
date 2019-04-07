package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartRepository;
import com.javaguru.shoppinglist.repository.HibernateCartRepository;
import com.javaguru.shoppinglist.service.validation.CartRepositorySizeValidator;
import com.javaguru.shoppinglist.service.validation.CartValidation.CartValidationService;
import com.javaguru.shoppinglist.service.validation.ProductNotNullValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepo;
    private final CartValidationService validationService;

    @Autowired
    public CartService(CartRepository cartRepo, CartValidationService validationService) {
        this.cartRepo = cartRepo;
        this.validationService = validationService;
    }
@Transactional
    public String createCart(String name) {
        ShoppingCart cart = new ShoppingCart();
        cart.setName(name);
        validationService.validate(cart);
        ShoppingCart createdCart = cartRepo.createCart(cart);
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
        productToDelete.orElseThrow(() ->
                new IllegalArgumentException("Product, named " + productNameToDelete + " not found"));
        cartRepo.deleteProductFromCart(cartName, productToDelete.get());
    }

    public void addProductToCart(String cartName, Optional<Product> product) {
        new ProductNotNullValidator().validate(product);
        cartRepo.addProductToCart(cartName, product.get());
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

    public ShoppingCart findCartById(Long cartId) {
        return cartRepo.findCartById(cartId)
                .orElseThrow(() -> new NoSuchElementException("Cart not found, id: " + cartId));
    }

    public ShoppingCart findCartByName(String cartName) {
        return cartRepo.getCart(cartName)
                .orElseThrow(() -> new NoSuchElementException("Cart not found, id: " + cartName));
    }
}
