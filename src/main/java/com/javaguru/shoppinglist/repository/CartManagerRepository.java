package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.console.*;
import com.javaguru.shoppinglist.service.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class CartManagerRepository {

    private final ProductService productService;
    private final CartService cartService;
    private Map<String, CartManager> cartManager = new LinkedHashMap<>();

    public CartManagerRepository(ProductService service, CartService shoppingCartService) {
        this.productService = service;
        this.cartService = shoppingCartService;
        cartManager.put("1. Add product to ", new CartAddProductManager(productService, cartService));
        cartManager.put("2. Delete product from ", new CartDeleteProductManager(cartService));
        cartManager.put("3. Get list of products in ", new CartPrintContentManager(cartService));
        cartManager.put("4. Get total actual price of products in ", new CartTotalPriceManager(cartService));
        cartManager.put("5. Remove all from ", new CartRemoveContentManager(cartService));
        cartManager.put("6. Delete ", new CartDeleteManager(cartService));
    }

    public int getCartManagerSize(){return cartManager.size();}

    public void printCartManagerMenu(String cartName){
        cartManager.keySet().stream().forEach(element -> System.out.println(element + " '" + cartName + "'"));
    }

    public void runCartManagerMenuChoice(int response, String cartName){
        cartManager.values().stream().skip(response - 1).findFirst().get().manageCart(cartName);
    }
}
