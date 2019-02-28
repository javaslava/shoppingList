package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.service.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class CartManagerRepository {

    private ProductRepository productRepository;
    private CartRepository shoppingCartRepo;

    private Map<String, CartManager> cartManager = new LinkedHashMap<>();

    public CartManagerRepository(ProductRepository productRepo, CartRepository cartRepo) {
        this.productRepository = productRepo;
        this.shoppingCartRepo = cartRepo;
        cartManager.put("1. Add product to ", new CartAddProductManager(productRepository, shoppingCartRepo));
        cartManager.put("2. Delete product from ", new CartDeleteProductManager(shoppingCartRepo));
        cartManager.put("3. Get list of products in ", new CartPrintContentManager(shoppingCartRepo));
        cartManager.put("4. Get total actual price of products in ", new CartTotalPriceManager(shoppingCartRepo));
        cartManager.put("5. Remove all from ", new CartRemoveContentManager(shoppingCartRepo));
        cartManager.put("6. Delete ", new CartDeleteManager(shoppingCartRepo));
    }

    public int getCartManagerSize(){return cartManager.size();}

    public void printCartManagerMenu(String cartName){
        cartManager.keySet().stream().forEach(element -> System.out.println(element + " '" + cartName + "'"));
    }

    public void runCartManagerMenuChoice(int response, String cartName){
        cartManager.values().stream().skip(response - 1).findFirst().get().manageCart(cartName);
    }

}
