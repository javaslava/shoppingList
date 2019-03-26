package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CartRepository {
    private Map<String, List<Product>> shoppingCarts = new LinkedHashMap<>();

    public ShoppingCart insert(ShoppingCart cart) {
        shoppingCarts.put(cart.getName(), new ArrayList<>());
        return cart;
    }

    public boolean checkForCartByName(String cartName) {
        return shoppingCarts.containsKey(cartName);
    }

    public void removeCartContent(String cartName) {
        shoppingCarts.get(cartName).clear();
    }

    public int getShoppingCartRepoSize() {
        return shoppingCarts.size();
    }

    public String getShoppingCartsNames() {
        return shoppingCarts.keySet().stream()
                .map(String::valueOf).collect(Collectors.joining(", "));
    }

    public void deleteCartByName(String cartName) {
        shoppingCarts.remove(cartName);
    }

    public void addProductToCart(String cartName, Product product) {
        shoppingCarts.get(cartName).add(product);
    }

    public Optional<Product> getProductByName(String cartName, String productName) {
        return shoppingCarts.get(cartName).stream()
                .filter((p) -> p.getName().equals(productName)).findFirst();
    }

    public void deleteProductFromCart(String cartName, Product product) {
        shoppingCarts.get(cartName).remove(product);
    }

    public void printCartContent(String cartName) {
        shoppingCarts.get(cartName).stream()
                .forEach(element -> System.out.println(element));
    }

    public BigDecimal getTotalCartPrice(String cartName) {
        return shoppingCarts.get(cartName).stream()
                .map(Product::getActualPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
