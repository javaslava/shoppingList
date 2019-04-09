package com.javaguru.shoppinglist.repository;

//import com.javaguru.shoppinglist.domain.CartContent;
//import com.javaguru.shoppinglist.domain.CartContent;

import com.javaguru.shoppinglist.domain.CartContent;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
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
    public ShoppingCart createCart(ShoppingCart cart) {
        sessionFactory.getCurrentSession().save(cart);
        return cart;
    }

    @Override
    public boolean checkForCartByName(String cartName) {
        String query = "select case when count(*)> 0 " +
                "then true else false end " +
                "from ShoppingCart where cartName='" + cartName + "'";
        return (boolean) sessionFactory.getCurrentSession().createQuery(query)
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    public void removeCartContent(String cartName) {
        Long cartId = getCartByName(cartName).get().getId();
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete from CartContent where cart_id=" + cartId);
        query.executeUpdate();
    }

    @Override
    public int getShoppingCartRepoSize() {
        List query = sessionFactory.getCurrentSession().createQuery("from ShoppingCart")
                .list();
        return query.size();
    }

    @Override
    public String getShoppingCartsNames() {
        Query query = sessionFactory.getCurrentSession()
                .createSQLQuery("select cartName from shoppingCarts");
        List<String> cartNames = query.list();
        return String.join(", ", cartNames);
    }

    @Override
    public void deleteCartByName(String cartName) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete ShoppingCart where cartName = '" + cartName + "'");
        query.executeUpdate();
    }

    @Override
    public void addProductToCart(String cartName, Product product) {
        CartContent content = new CartContent();
        content.setCartId(getCartByName(cartName).get().getId());
        content.setProductId(product.getId());
        sessionFactory.getCurrentSession().persist(content);
        // Long cartId = getCart(cartName).get().getId();
        //ShoppingCart cart = getCart(cartName).get();
//CartContent content = new CartContent();
//content.setCart(cartId);
//content.setProduct(product.getId());
//sessionFactory.getCurrentSession().save(content);


//Query query = sessionFactory.getCurrentSession()
//        .createQuery("INSERT INTO cartsContent (cart_id, product_id) '"+cartId+"', '"+product.getId()+ "'");
//query.executeUpdate();

//        String query = "INSERT INTO cartsContent (cart_id, product_id) " +
//                "VALUES ((SELECT id_cart FROM shoppingCarts WHERE cartName='" + cartName + "'), " +
//                "(" + getProductID(product.getName()) + "))";
//        jdbcTemplate.update(query);

//Query query1 = sessionFactory.getCurrentSession()
//        .createSQLQuery("INSERT INTO cartsContent (cart_id, product_id) VALUES (:cart_id, :product_id)");
//query1.setParameter("cart_id", cartId);
//query1.setParameter("product_id", product.getId());
//query1.executeUpdate();


//        String query = "INSERT INTO cartsContent (cart_id, product_id) " +
//                "VALUES ((SELECT id_cart FROM shoppingCarts WHERE cartName='" + cartName + "'), " +
//                "(" + getProductID(product.getName()) + "))";
//        jdbcTemplate.update(query);


    }

    private String getProductID(String productName) {
        return "SELECT id_product FROM products WHERE name='" + productName + "'";
    }

    @Override
    public Optional<Product> getProductByName(String cartName, String productName) {
        List products = sessionFactory.getCurrentSession()
                .createQuery("from Product where name = '" + productName + "'")
                .list();
        Product product = (Product) products.get(0);
        List cartContent = sessionFactory.getCurrentSession()
                .createQuery("from CartContent where product_id = '" + product.getId() + "'")
                .list();
        if (!cartContent.isEmpty()) {
            return Optional.of(product);
        }
        return Optional.empty();
    }


    @Override
    public void deleteProductFromCart(String cartName, Product product) {

        List query = sessionFactory.getCurrentSession()
                .createQuery("from CartContent where product_id =" + product.getId()).list();
        CartContent cart = (CartContent) query.get(0);
        sessionFactory.getCurrentSession().delete(cart);

//        CartContent cart = (CartContent)sessionFactory.getCurrentSession()
//                .createCriteria(CartContent.class)
//                .add(Restrictions.eq("product_id", product.getId()))
//                .setMaxResults(1)
//                .uniqueResult();
//        sessionFactory.getCurrentSession().remove(cart);
//
//        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CartContent.class);
//        criteria.add(Restrictions.eq("product_id", product.getId()));
//
//        CartContent cartToDelete = (CartContent)criteria.list().get(0);
//
//
//        sessionFactory.getCurrentSession().delete(cartToDelete);


//        String query = "delete from cartsContent where product_id = '" + product.getId() + "' LIMIT 1";
//        sessionFactory.getCurrentSession().createSQLQuery(query).executeUpdate();
    }

    @Override
    public void printCartContent(String cartName) {
        for (CartContent element : getCartContentList(cartName)) {
            Product product = getProductById(element.getProductId()).get();
            System.out.println(product);
        }
    }

    @Override
    public BigDecimal getTotalCartPrice(String cartName) {
        BigDecimal sum = new BigDecimal(0);
        for (CartContent element : getCartContentList(cartName)) {
            Product product = getProductById(element.getProductId()).get();
            sum = sum.add(product.getActualPrice());
        }
        return sum;
    }

    private List<CartContent> getCartContentList(String cartName) {
        Long cartId = getCartByName(cartName).get().getId();
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CartContent.class)
                .add(Restrictions.eq("cartID", cartId));
        return criteria.list();
    }

    private Optional<ShoppingCart> getCartByName(String cartName) {
        List carts = sessionFactory.getCurrentSession()
                .createQuery("from ShoppingCart WHERE cartName = '" + cartName + "'")
                .list();
        if (!carts.isEmpty()) {
            return Optional.of((ShoppingCart) carts.get(0));
        }
        return Optional.empty();
    }

    private Optional<Product> getProductById(Long productId) {
        List products = sessionFactory.getCurrentSession()
                .createQuery("from Product where id = '" + productId + "'")
                .list();
        Product product = (Product) products.get(0);
        return Optional.of(product);
    }

    public Optional<ShoppingCart> findCartById(Long id) {
        ShoppingCart cart = (ShoppingCart) sessionFactory.getCurrentSession().createCriteria(ShoppingCart.class)
                .add(Restrictions.eq("id_cart", id))
                .uniqueResult();
        return Optional.ofNullable(cart);
    }
}
