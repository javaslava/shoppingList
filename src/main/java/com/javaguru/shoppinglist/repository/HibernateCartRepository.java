package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;
@Repository
@Profile("hibernate")
@Transactional
public class HibernateCartRepository implements CartRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateCartRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    @Override
    public ShoppingCart createCart(ShoppingCart cart){
        return cart;
    }
    @Override
    public boolean checkForCartByName(String cartName){
return false;
    }
    @Override
    public void removeCartContent(String cartName){

    }
    @Override
    public int getShoppingCartRepoSize(){
return 1;
    }
   @Override
    public String getShoppingCartsNames(){
return "";
    }
    @Override
    public void deleteCartByName(String cartName){

    }
    @Override
   public void addProductToCart(String cartName, Product product){

    }
    @Override
    public Optional<Product> getProductByName(String cartName, String productName){
return Optional.empty();
    }
    @Override
    public void deleteProductFromCart(String cartName, Product product){

    }
    @Override
   public void printCartContent(String cartName){

    }
    @Override
    public BigDecimal getTotalCartPrice(String cartName){
        BigDecimal sum = new BigDecimal(0);
        return sum;
    }
}
