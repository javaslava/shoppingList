package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Category;
import com.javaguru.shoppinglist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("localdb")
public class LocalDBProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocalDBProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long insert(Product product) {
        createProductsTable();
        createCategoriesTable();
        String query =
                "INSERT INTO products (name, price, actualPrice, description, discount, categoryID) values (" +
                        "?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setBigDecimal(2, product.getPrice());
            ps.setBigDecimal(3, product.getActualPrice());
            ps.setString(4, product.getDescription());
            ps.setBigDecimal(5, product.getDiscount());
            ps.setInt(6, getCategoryID(product));
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return queryForProduct(id, "id");
    }

    @Override
    public Optional<Product> getProductByName(String productName) {
        return queryForProduct(productName, "name");
    }

    @Override
    public boolean existsByName(Product product) {
        createProductsTable();
        String query =
                "SELECT CASE WHEN count(*)> 0 " +
                        "THEN true ELSE false END FROM products WHERE name='" + product.getName() + "'";
        return jdbcTemplate.queryForObject(query, Boolean.class);
    }

    private Optional<Product> queryForProduct(Object obj, String field) {
        createProductsTable();
        createCategoriesTable();
        String query = "SELECT products.id, name, price, actualPrice, description, discount, category " +
                "FROM products, categories " +
                "WHERE products." + field + "='" + obj + "' AND categories.id = products.categoryID";
        List<Product> products = jdbcTemplate.query(query, new BeanPropertyRowMapper(Product.class));
        if (!products.isEmpty()) {
            return Optional.ofNullable(products.get(0));
        }
        return Optional.empty();
    }

     void createProductsTable() {
        String query = "CREATE TABLE IF NOT EXISTS products (\n" +
                "  id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  name VARCHAR(32) NOT NULL,\n" +
                "  price DECIMAL(10,2) NOT NULL,\n" +
                "  actualPrice DECIMAL(10,2) NOT NULL,\n" +
                "  description VARCHAR(50) NULL,\n" +
                "  discount DECIMAL(10,2) NOT NULL,\n" +
                "  categoryID INT NOT NULL,\n" +
                "  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
                "  PRIMARY KEY (id)\n" +
                ")";
        jdbcTemplate.execute(query);
    }

    private void createCategories() {
        final Enum[] categories = Category.values();
        for (Enum element : categories) {
            String query =
                    "SELECT CASE WHEN count(*)> 0 " +
                            "THEN true ELSE false END FROM categories WHERE category ='" + element + "'";
            if (!jdbcTemplate.queryForObject(query, Boolean.class)) {
                String sql = "INSERT INTO categories(category) values ('" + element + "')";
                jdbcTemplate.update(sql);
            }
        }
    }

    void createCategoriesTable() {
        String query = "CREATE TABLE IF NOT EXISTS categories(" +
                "id BIGINT NOT NULL AUTO_INCREMENT, " +
                "category VARCHAR(15) NOT NULL, " +
                "PRIMARY KEY (id))";
        jdbcTemplate.execute(query);
        createCategories();
    }

    private Integer getCategoryID(Product product) {
        String query = "SELECT id FROM categories WHERE category = '" + product.getCategory() + "'";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
}
