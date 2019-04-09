package com.javaguru.shoppinglist.console.cartManager;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
//import com.javaguru.shoppinglist.service.CartContentService;
import com.javaguru.shoppinglist.service.CartService;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CartAddProductManager implements CartManager {

    private final CartService cartService;
    private final ProductService productService;
    //private final CartContentService contentService;

    @Autowired
    public CartAddProductManager(ProductService productService, CartService cartService
            //, CartContentService contentService
    ) {
        this.cartService = cartService;
        this.productService = productService;
        //this.contentService = contentService;
    }

    @Override
    public void manageCart(String cartName) {
        System.out.println("Enter product name to add to " + cartName);
        Optional<Product> productToAdd = productService.getProductByName(userStringInput());
        cartService.addProductToCart(cartName, productToAdd);
//ShoppingCart cart = cartService.findCartByName(cartName);
//        contentService.assignContent(cart.getId(), productToAdd.get().getId());
    }
}
