package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile("inmemory")
public class InMemoryCartRepository implements CartRepository {
    private Map<String, List<Product>> shoppingCarts = new LinkedHashMap<>();

    @Override
    public ShoppingCart createCart(ShoppingCart cart) {
        shoppingCarts.put(cart.getName(), new ArrayList<>());
        return cart;
    }

   @Override
    public boolean checkForCartByName(String cartName) {
        return shoppingCarts.containsKey(cartName);
    }

    @Override
    public void removeCartContent(String cartName) {
        shoppingCarts.get(cartName).clear();
    }

    @Override
    public int getShoppingCartRepoSize() {
        return shoppingCarts.size();
    }

    @Override
    public String getShoppingCartsNames() {
        return shoppingCarts.keySet().stream()
                .map(String::valueOf).collect(Collectors.joining(", "));
    }

    @Override
    public void deleteCartByName(String cartName) {
        shoppingCarts.remove(cartName);
    }

    @Override
    public void addProductToCart(String cartName, Product product) {
        shoppingCarts.get(cartName).add(product);
    }

    @Override
    public Optional<Product> getProductByName(String cartName, String productName) {
        return shoppingCarts.get(cartName).stream()
                .filter((p) -> p.getName().equals(productName)).findFirst();
    }

    @Override
    public void deleteProductFromCart(String cartName, Product product) {
        shoppingCarts.get(cartName).remove(product);
    }

    @Override
    public void printCartContent(String cartName) {
        shoppingCarts.get(cartName).stream()
                .forEach(element -> System.out.println(element));
    }

    @Override
    public BigDecimal getTotalCartPrice(String cartName) {
        return shoppingCarts.get(cartName).stream()
                .map(Product::getActualPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
