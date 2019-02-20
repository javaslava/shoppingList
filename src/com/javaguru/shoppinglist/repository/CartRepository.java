package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;
import java.util.*;

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

    public Set<String> getShoppingCartsNames() {
        return shoppingCarts.keySet();
    }

    public void deleteCartByName(String cartName) {
        shoppingCarts.remove(cartName);
    }

    public void addProductToCart(String cartName, Product product) {
        shoppingCarts.get(cartName).add(product);
    }

    public Product getProductByName(String cartName, String productName) {
        for (Product element : shoppingCarts.get(cartName)) {
            if (element.getName().equals(productName)) {
                return element;
            }
        }
        return null;
    }

    public void deleteProductFromCart(String cartName, Product product) {
        shoppingCarts.get(cartName).remove(product);
    }

    public void printCartContent(String cartName) {
        for (Product element : shoppingCarts.get(cartName)) {
            System.out.println(element.getName() + ": " + element.getDescription() + " Price: " + element.getPrice() + " Discount: " + element.getDiscount() + " Actual price: " + element.getActualPrice());
        }
    }

    public BigDecimal getTotalCartPrice(String cartName) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Product element : shoppingCarts.get(cartName)) {
            totalPrice = totalPrice.add(element.getActualPrice());
        }
        return totalPrice;
    }
}
