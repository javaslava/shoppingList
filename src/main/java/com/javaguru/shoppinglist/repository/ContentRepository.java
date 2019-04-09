package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.CartContent;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ContentRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public ContentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(CartContent cartContent) {
        sessionFactory.getCurrentSession().persist(cartContent);
    }
}
