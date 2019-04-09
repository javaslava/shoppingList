package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.HibernateCartRepository;
import com.javaguru.shoppinglist.service.validation.CartRepositorySizeValidator;
import com.javaguru.shoppinglist.service.validation.CartValidation.CartValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartService {
    private final CartValidationService validationService;
    private final HibernateCartRepository cartRepo;

    @Autowired
    public CartService(CartValidationService validationService,
                       HibernateCartRepository cartRepo) {
        this.validationService = validationService;
        this.cartRepo = cartRepo;
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
        ShoppingCart cart = getCartByName(cartName).get();
        Optional<Product> productToDelete = cartRepo.getProductByName(cart, productNameToDelete);
        productToDelete.orElseThrow(() ->
                new IllegalArgumentException("Product, named " + productNameToDelete + " not found"));
        cartRepo.deleteProductFromCart(cart, productToDelete.get());
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

    public Optional<ShoppingCart> getCartByName(String cartName) {
        return cartRepo.getCartByName(cartName);
    }
}
