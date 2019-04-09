package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.CartContent;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.HibernateContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartContentService {
    private final HibernateContentRepository cartContentRepository;

    @Autowired
    CartContentService(HibernateContentRepository cartContentRepository) {
        this.cartContentRepository = cartContentRepository;
    }

    public void addContent(ShoppingCart cart, Product product) {
        CartContent cartContent = new CartContent();
        cartContent.setCart(cart);
        cartContent.setProduct(product);
        cartContentRepository.save(cartContent);
    }
}

