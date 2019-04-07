package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.CartContent;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Profile("hibernate")
@Transactional
public class HibernateContentRepository {

    private final SessionFactory sessionFactory;
@Autowired
    public HibernateContentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
   }

   public Long save(CartContent cartContent) {
       sessionFactory.getCurrentSession().save(cartContent);
        return cartContent.getId();
    }
}
