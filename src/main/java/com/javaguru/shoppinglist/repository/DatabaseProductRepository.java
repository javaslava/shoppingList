package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class DatabaseProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //@Override
    public Product insert(Product product) {
        String query = "insert into product (name, description) values (" +
                "?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            return ps;
        }, keyHolder);

        //return keyHolder.getKey().longValue();
        return product;
    }


    //@Override
//    public Product findProductById(Long id) {
//        String query = "select * from tasks where id=" + id;
//        List<Product> product = jdbcTemplate.query(query,
//                new BeanPropertyRowMapper(Product.class));
//        if (!product.isEmpty()) {
//            return null;
//        }
//        return product;
 //   }

  //  @Override
    public boolean existsByName(String name) {
        String query = "SELECT CASE WHEN count(*)> 0 " +
                "THEN true ELSE false END " +
                "FROM tasks where name=" + name;
        return jdbcTemplate.queryForObject(query, Boolean.class);
    }

   // @Override
  //  public Optional<Task> findTaskByName(String name) {
   //     return Optional.empty();
   // }








}
