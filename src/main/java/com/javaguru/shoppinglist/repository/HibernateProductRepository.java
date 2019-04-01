package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Category;
import com.javaguru.shoppinglist.domain.Product;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Profile("hibernate")
@Transactional
public class HibernateProductRepository implements ProductRepository {

    private final SessionFactory sessionFactory;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HibernateProductRepository(SessionFactory sessionFactory, JdbcTemplate jdbcTemplate) {
        this.sessionFactory = sessionFactory;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long insert(Product product) {
        createCategories();
        sessionFactory.getCurrentSession().save(product);
        return product.getId();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        Product product = (Product) sessionFactory.getCurrentSession().createCriteria(Product.class)
                .add(Restrictions.eq("id_product", id))
                .uniqueResult();
        return Optional.ofNullable(product);
    }

   @Override
    public boolean existsByName(Product product) {
        String query = "select case when count(*)> 0 " +
                "then true else false end " +
                "from Product p where p.name='" + product.getName() + "'";
        // String query = "SELECT name FROM products WHERE name='" + product.getName() +"'";
        //String query = "SELECT EXISTS(SELECT name FROM products WHERE name='" + product.getName() +"')";
//        return (boolean) sessionFactory.getCurrentSession().createQuery(query)
//                .setMaxResults(1)
//                .uniqueResult();
//        return (boolean) sessionFactory.getCurrentSession().createQuery(query)
//                .setMaxResults(1)
//                .uniqueResult();


        Query query1 = sessionFactory.getCurrentSession().createQuery(query);
        return (Boolean) query1.list().get(0);

//        sessionFactory.getCurrentSession().createQuery("select from products where name=" + product.getName())
//        Query query = session.createQuery("select name from User where name = :userName");
//        query.setParameter("userName", user.getName());
//
//        if (((org.hibernate.query.Query) query).list().isEmpty()) {

        //return true;
    }

    @Override
    public Optional<Product> getProductByName(String productName) {
        Product product = (Product) sessionFactory.getCurrentSession().createCriteria(Product.class)
                .add(Restrictions.eq("name", productName))
                .uniqueResult();
        return Optional.ofNullable(product);
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

}
