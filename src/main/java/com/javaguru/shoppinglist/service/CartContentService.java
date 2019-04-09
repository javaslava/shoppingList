package com.javaguru.shoppinglist.service;


import com.javaguru.shoppinglist.domain.CartContent;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.HibernateContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CartContentService {

    private final CartService cartService;
    private final ProductService productService;
    private final HibernateContentRepository cartContentRepository;

    @Autowired
    CartContentService(CartService cartService,
                       ProductService productService,
                       HibernateContentRepository cartContentRepository) {
        this.cartService = cartService;
        this.productService = productService;
        this.cartContentRepository = cartContentRepository;
    }

//    public Long assignContent(Long cartId, Long productId) {
//        Product product = productService.findProductById(productId);
//        ShoppingCart cart = cartService.findCartById(cartId);
//        CartContent cartContent = new CartContent();
//        cartContent.setCartId(cart);
//        cartContent.setProductId(product);
//        return cartContentRepository.save(cartContent);
//    }
}

