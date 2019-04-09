package com.javaguru.shoppinglist.domain;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cartsContent")
public class CartContent {

    @Id
    @Column(name = "id_cartContent")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "cart_id", nullable = false)
//    private ShoppingCart cart;


    @Column(name = "cart_id", nullable = false)
    private Long cartID;

//    @ManyToOne
//    @JoinColumn(name = "product_id", nullable = false)
//    private Product product;


    @Column(name = "product_id", nullable = false)
    private Long productID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartID;
    }
    public void setCartId(Long cartID) {
        this.cartID = cartID;
    }

    public Long getProductId() {
        return productID;
    }

    public void setProductId(Long productID) {
        this.productID = productID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartContent cartContent = (CartContent) o;
        return Objects.equals(id, cartContent.id) &&
                Objects.equals(cartID, cartContent.cartID) &&
                Objects.equals(productID, cartContent.productID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartID, productID);
    }

    @Override
    public String toString() {
        return "CartContent{" +
                "id=" + id +
                ", cart=" + cartID +
                ", product=" + productID +
                '}';
    }
}
