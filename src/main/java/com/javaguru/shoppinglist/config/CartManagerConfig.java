package com.javaguru.shoppinglist.config;

import com.javaguru.shoppinglist.console.cartManager.CartManager;
import com.javaguru.shoppinglist.repository.CartManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class CartManagerConfig {
    private final CartManager cartAddProductManager;
    private final CartManager cartDeleteProductManager;
    private final CartManager cartPrintContentManager;
    private final CartManager cartTotalPriceManager;
    private final CartManager cartRemoveContentManager;
    private final CartManager cartDeleteManager;

    @Autowired
    public CartManagerConfig(CartManager cartAddProductManager, CartManager cartDeleteProductManager,
                             CartManager cartPrintContentManager, CartManager cartTotalPriceManager,
                             CartManager cartRemoveContentManager, CartManager cartDeleteManager) {
        this.cartAddProductManager = cartAddProductManager;
        this.cartDeleteProductManager = cartDeleteProductManager;
        this.cartPrintContentManager = cartPrintContentManager;
        this.cartTotalPriceManager = cartTotalPriceManager;
        this.cartRemoveContentManager = cartRemoveContentManager;
        this.cartDeleteManager = cartDeleteManager;
    }

    @Bean
    public CartManagerRepository cartManagerRepository() {
        Map<String, CartManager> cartManagerMenu = new LinkedHashMap<>();
        cartManagerMenu.put("1. Add product to ", cartAddProductManager);
        cartManagerMenu.put("2. Delete product from ", cartDeleteProductManager);
        cartManagerMenu.put("3. Get list of products in ", cartPrintContentManager);
        cartManagerMenu.put("4. Get total actual price of products in ", cartTotalPriceManager);
        cartManagerMenu.put("5. Remove all from ", cartRemoveContentManager);
        cartManagerMenu.put("6. Delete ", cartDeleteManager);
        return new CartManagerRepository(cartManagerMenu);
    }
}
