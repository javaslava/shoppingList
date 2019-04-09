package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.CartContent;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartContentService {
    private final ContentRepository cartContentRepository;

    @Autowired
    CartContentService(ContentRepository cartContentRepository) {
        this.cartContentRepository = cartContentRepository;
    }

    public void addContent(ShoppingCart cart, Product product) {
        CartContent cartContent = new CartContent();
        cartContent.setCart(cart);
        cartContent.setProduct(product);
        cartContentRepository.save(cartContent);
    }
}

