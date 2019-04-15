package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.CartContent;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CartRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public CartRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ShoppingCart createCart(ShoppingCart cart) {
        sessionFactory.getCurrentSession().save(cart);
        return cart;
    }

    public boolean checkForCartByName(String cartName) {
        String query = "select case when count(*)> 0 " +
                "then true else false end " +
                "from ShoppingCart where cartName='" + cartName + "'";
        return (boolean) sessionFactory.getCurrentSession().createQuery(query)
                .setMaxResults(1)
                .uniqueResult();
    }

    public void removeCartContent(String cartName) {
        Long cartId = getCartByName(cartName).get().getId();
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete from CartContent where cart_id=" + cartId);
        query.executeUpdate();
    }

    public int getShoppingCartRepoSize() {
        List query = sessionFactory.getCurrentSession().createQuery("from ShoppingCart")
                .list();
        return query.size();
    }

    public String getShoppingCartsNames() {
        Query query = sessionFactory.getCurrentSession()
                .createSQLQuery("select cartName from shoppingCarts");
        List<String> cartNames = query.list();
        return String.join(", ", cartNames);
    }

    public void deleteCartByName(String cartName) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete ShoppingCart where cartName = '" + cartName + "'");
        query.executeUpdate();
    }

    public Optional<Product> getProductByName(ShoppingCart cart, String productName) {
        Product product = (Product) getProductListByName(productName).get(0);
        if (!getContentListByProductId(cart, product).isEmpty()) {
            return Optional.of(product);
        }
        return Optional.empty();
    }

    public void deleteProductFromCart(ShoppingCart cart, Product product) {
        CartContent content = (CartContent) getContentListByProductId(cart, product).get(0);
        sessionFactory.getCurrentSession().delete(content);
    }

    public void printCartContent(ShoppingCart cart) {
        for (CartContent element : getCartContentList(cart)) {
            Product product = getProductById(element.getProduct().getId()).get();
            System.out.println(product);
        }
    }

    public BigDecimal getTotalCartPrice(ShoppingCart cart) {
        BigDecimal sum = new BigDecimal(0);
        for (CartContent element : getCartContentList(cart)) {
            Product product = getProductById(element.getProduct().getId()).get();
            sum = sum.add(product.getActualPrice());
        }
        return sum;
    }

    public Optional<ShoppingCart> getCartByName(String cartName) {
        List carts = sessionFactory.getCurrentSession()
                .createQuery("from ShoppingCart WHERE cartName = '" + cartName + "'")
                .list();
        if (!carts.isEmpty()) {
            return Optional.of((ShoppingCart) carts.get(0));
        }
        return Optional.empty();
    }

    private List<CartContent> getCartContentList(ShoppingCart cart) {

        List cartContentList = sessionFactory.getCurrentSession()
                .createQuery("from CartContent where cart_id =" + cart.getId())
                .list();
        return cartContentList;
    }

    private List getProductListByName(String productName) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Product where name = '" + productName + "'")
                .list();
    }

    private List getContentListByProductId(ShoppingCart cart, Product product) {
        return sessionFactory.getCurrentSession()
                .createQuery("from CartContent where product_id =" + product.getId() +
                        "and cart_id =" + cart.getId())
                .list();
    }

    private Optional<Product> getProductById(Long productId) {
        List products = sessionFactory.getCurrentSession()
                .createQuery("from Product where id = '" + productId + "'")
                .list();
        Product product = (Product) products.get(0);
        return Optional.of(product);
    }
}
