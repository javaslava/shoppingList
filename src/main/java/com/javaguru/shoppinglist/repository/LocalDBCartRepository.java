package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
@Profile("localdb")
public class LocalDBCartRepository implements CartRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocalDBCartRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ShoppingCart createCart(ShoppingCart cart) {
        String query = "INSERT INTO shoppingCarts(cartName) values ('" + cart.getName() + "')";
        jdbcTemplate.update(query);
        return cart;
    }

    @Override
    public boolean checkForCartByName(String cartName) {
        String query =
                "SELECT CASE WHEN count(*)> 0 " +
                        "THEN true ELSE false END " +
                        "FROM shoppingCarts WHERE cartName='" + cartName + "'";
        return jdbcTemplate.queryForObject(query, Boolean.class);
    }

    @Override
    public void removeCartContent(String cartName) {
        String query = "DELETE FROM cartsContent WHERE cart_id=" +
                "(SELECT id_cart FROM shoppingCarts WHERE cartName='" + cartName + "')";
        jdbcTemplate.execute(query);
    }

    @Override
    public void deleteCartByName(String cartName) {
        removeCartContent(cartName);
        String query = "DELETE FROM shoppingCarts WHERE cartName='" + cartName + "'";
        jdbcTemplate.execute(query);
    }

    @Override
    public int getShoppingCartRepoSize() {
        String query = "SELECT cartName FROM shoppingCarts";
        List<String> carts = jdbcTemplate.query(query,
                new BeanPropertyRowMapper(String.class));
        if (!carts.isEmpty()) {
            return carts.size();
        }
        return 0;
    }

    @Override
    public String getShoppingCartsNames() {
        String query = "SELECT cartName FROM shoppingCarts";
        List<String> carts = jdbcTemplate.queryForList(query, String.class);
        return String.join(", ", carts);
    }

    @Override
    public void addProductToCart(String cartName, Product product) {
        String query = "INSERT INTO cartsContent (cart_id, product_id) " +
                "VALUES ((SELECT id_cart FROM shoppingCarts WHERE cartName='" + cartName + "'), " +
                "(" + getProductID(product.getName()) + "))";
        jdbcTemplate.update(query);
    }

    @Override
    public void deleteProductFromCart(String cartName, Product product) {
        String query = "DELETE FROM cartsContent WHERE product_id=(" + getProductID(product.getName()) + ") " +
                "AND cart_id=(SELECT id_cart FROM shoppingCarts WHERE cartName='" + cartName + "') LIMIT 1";
        jdbcTemplate.execute(query);
    }

    @Override
    public void printCartContent(String cartName) {
        for (Long element : getProductIDlist(cartName)) {
            String query = "SELECT  name, price, actualPrice, description, discount, category " +
                    "FROM products, categories " +
                    "WHERE id_product='" + element + "' AND id_category=category_id";
            Product product = (Product) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper(Product.class));
            product.setId(element);
            System.out.println(product);
        }
    }

    @Override
    public Optional<Product> getProductByName(String cartName, String productName) {
        String query = "SELECT id_product, name, price, actualPrice, description, discount, category " +
                "FROM products, categories, cartsContent " +
                "WHERE name='" + productName + "' AND product_id = id_product";
        List<Product> products = jdbcTemplate.query(query, new BeanPropertyRowMapper(Product.class));
        if (!products.isEmpty()) {
            return Optional.ofNullable(products.get(0));
        }
        return Optional.empty();
    }

    @Override
    public BigDecimal getTotalCartPrice(String cartName) {
        BigDecimal sum = new BigDecimal(0);
        for (Long element : getProductIDlist(cartName)) {
            String query = "SELECT actualPrice FROM products WHERE id_product='" + element + "'";
            sum = sum.add(jdbcTemplate.queryForObject(query, BigDecimal.class));
        }
        return sum;
    }

    private String getProductID(String productName) {
        return "SELECT id_product FROM products WHERE name='" + productName + "'";
    }

    private Long getCartID(String cartName) {
        String queryForCartID = "SELECT id_cart FROM shoppingCarts WHERE cartName='" + cartName + "'";
        return jdbcTemplate.queryForObject(queryForCartID, Long.class);
    }

    private List<Long> getProductIDlist(String cartName) {
        String queryForProductID = "SELECT product_id FROM cartsContent WHERE cart_id='" + getCartID(cartName) + "'";
        return jdbcTemplate.queryForList(queryForProductID, Long.class);
    }
}
