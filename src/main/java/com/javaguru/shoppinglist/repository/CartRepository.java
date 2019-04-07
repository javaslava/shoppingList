package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;

import java.math.BigDecimal;
import java.util.Optional;

public interface CartRepository {

    ShoppingCart createCart(ShoppingCart cart);

    boolean checkForCartByName(String cartName);

    void removeCartContent(String cartName);

    int getShoppingCartRepoSize();

    String getShoppingCartsNames();

    void deleteCartByName(String cartName);

    void addProductToCart(String cartName, Product product);

    Optional<Product> getProductByName(String cartName, String productName);

    void deleteProductFromCart(String cartName, Product product);

    void printCartContent(String cartName);

    BigDecimal getTotalCartPrice(String cartName);

    Optional<ShoppingCart> findCartById(Long cartId);
    Optional<ShoppingCart> getCart(String cartName);
}
