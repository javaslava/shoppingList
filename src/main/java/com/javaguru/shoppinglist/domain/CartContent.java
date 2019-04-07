package com.javaguru.shoppinglist.domain;


import javax.persistence.*;
import java.util.Objects;

@Entity(name = "cartsContent")
@Table(name = "cartsContent")
public class CartContent {

    @Id
    @Column(name = "id_cartContent")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private ShoppingCart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShoppingCart getCart() {
        return cart;
    }
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartContent cartContent = (CartContent) o;
        return Objects.equals(id, cartContent.id) &&
                Objects.equals(cart, cartContent.cart) &&
                Objects.equals(product, cartContent.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cart, product);
    }

    @Override
    public String toString() {
        return "CartContent{" +
                "id=" + id +
                ", cart=" + cart +
                ", product=" + product +
                '}';
    }
}
