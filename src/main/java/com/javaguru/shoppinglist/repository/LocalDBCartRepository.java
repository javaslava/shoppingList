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
    private final LocalDBProductRepository productRepo;

    @Autowired
    public LocalDBCartRepository(JdbcTemplate jdbcTemplate, LocalDBProductRepository productRepo) {
        this.jdbcTemplate = jdbcTemplate;
        this.productRepo = productRepo;
    }

    @Override
    public ShoppingCart createCart(ShoppingCart cart) {
        createShoppingCartsTable();
        String query = "INSERT INTO shoppingCarts(cartName) values ('" + cart.getName() + "')";
        jdbcTemplate.update(query);
        return cart;
    }

    @Override
    public boolean checkForCartByName(String cartName) {
        createShoppingCartsTable();
        String query =
                "SELECT CASE WHEN count(*)> 0 " +
                        "THEN true ELSE false END " +
                        "FROM shoppingCarts WHERE cartName='" + cartName + "'";
        return jdbcTemplate.queryForObject(query, Boolean.class);
    }

    @Override
    public void removeCartContent(String cartName) {
        createCartContentTable();
        String query = "DELETE FROM cartContent WHERE cartContent.cartID=" +
                "(SELECT id FROM shoppingCarts WHERE cartName='" + cartName + "')";
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
        createShoppingCartsTable();
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
        createShoppingCartsTable();
        String query = "SELECT cartName FROM shoppingCarts";
        List<String> carts = jdbcTemplate.queryForList(query, String.class);
        return String.join(", ", carts);
    }

    @Override
    public void addProductToCart(String cartName, Product product) {
        createCartContentTable();
        String getCartID = "SELECT id FROM shoppingCarts WHERE cartName='" + cartName + "'";
        String query = "INSERT INTO cartContent (cartID, productID) VALUES ((" + getCartID + "), ?)";
        jdbcTemplate.update(query, product.getId());
    }

    @Override
    public Optional<Product> getProductByName(String cartName, String productName) {
        productRepo.createProductsTable();
        productRepo.createCategoriesTable();
        createCartContentTable();
        String query = "SELECT products.id, name, price, actualPrice, description, discount, category " +
                "FROM products, categories, cartContent " +
                "WHERE products.name='" + productName + "' AND productID = products.id";
        List<Product> products = jdbcTemplate.query(query, new BeanPropertyRowMapper(Product.class));
        if (!products.isEmpty()) {
            return Optional.ofNullable(products.get(0));
        }
        return Optional.empty();
    }

    @Override
    public void deleteProductFromCart(String cartName, Product product) {
        String query = "DELETE FROM cartContent WHERE productID = '" + product.getId() + "' " +
                "AND cartID=(SELECT id FROM shoppingCarts WHERE cartName='" + cartName + "')";
        jdbcTemplate.execute(query);
    }

    @Override
    public void printCartContent(String cartName) {
        productRepo.createProductsTable();
        productRepo.createCategoriesTable();
        createCartContentTable();
        for (Long element : getProductIDlist(cartName)) {
            String query = "SELECT products.id, name, price, actualPrice, description, discount, category " +
                    "FROM products, categories " +
                    "WHERE products.id='" + element + "' AND categories.id=products.categoryID";
            System.out.println(jdbcTemplate.query(query, new BeanPropertyRowMapper(Product.class)));
        }
    }

    @Override
    public BigDecimal getTotalCartPrice(String cartName) {
        productRepo.createProductsTable();
        productRepo.createCategoriesTable();
        createCartContentTable();
        BigDecimal sum = new BigDecimal(0);
        for (Long element : getProductIDlist(cartName)) {
            String query = "SELECT actualPrice FROM products WHERE id='" + element + "'";
            sum = sum.add(jdbcTemplate.queryForObject(query, BigDecimal.class));
        }
        return sum;
    }

    private Long getCartID(String cartName) {
        String queryForCartID = "SELECT id FROM shoppingCarts WHERE cartName='" + cartName + "'";
        return jdbcTemplate.queryForObject(queryForCartID, Long.class);
    }

    private List<Long> getProductIDlist(String cartName) {
        String queryForProductID = "SELECT productID FROM cartContent WHERE cartID='" + getCartID(cartName) + "'";
        return jdbcTemplate.queryForList(queryForProductID, Long.class);
    }

    private void createShoppingCartsTable() {
        String query = "CREATE TABLE IF NOT EXISTS shoppingCarts(" +
                "id BIGINT NOT NULL AUTO_INCREMENT, " +
                "cartName VARCHAR(15) NOT NULL, " +
                "PRIMARY KEY (id))";
        jdbcTemplate.execute(query);
    }

    private void createCartContentTable() {
        String query = "CREATE TABLE IF NOT EXISTS cartContent (\n" +
                "  id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  cartID BIGINT NOT NULL,\n" +
                "  productID BIGINT NOT NULL,\n" +
                "PRIMARY KEY (id))";
        jdbcTemplate.execute(query);
    }
}
