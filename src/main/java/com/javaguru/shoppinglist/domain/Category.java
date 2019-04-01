package com.javaguru.shoppinglist.domain;



import org.hibernate.annotations.Tuplizer;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@Tuplizer(impl = EnumTuplizer.class)

public enum Category {
    //@OneToOne
    FRUITS, VEGETABLES, NUTS, BEVERAGES, SWEETS;
    @Id
    @OneToOne(mappedBy="category")

    public String category = toString();

    }
