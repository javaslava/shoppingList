package com.javaguru.shoppinglist.domain;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "shoppingCarts")
public class ShoppingCart {
    @Id
    @Column(name = "id_cart")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "cartName", nullable = false, unique = true)
    private String name;

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "carts")
//    private Set<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ShoppingCart{id = " + id + ", name = '" + name + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart cart = (ShoppingCart) o;
        return Objects.equals(id, cart.id) &&
                Objects.equals(name, cart.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
