package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CartRepository {
    private Map<String, List<Product>> shoppingCarts = new HashMap<>();

    public void create(String cartName) {
        shoppingCarts.put(cartName, new ArrayList<>());
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
