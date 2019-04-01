package com.javaguru.shoppinglist.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "products")

public class Product {
    @Id
    @Column(name = "id_product")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private BigDecimal price;
//    @Enumerated(EnumType.ORDINAL)
//    @Column(name = "category_id", columnDefinition = "bigint")
   @OneToOne
   //@JoinColumn(name = "id_category")
   @JoinTable(name = "categories",
   joinColumns = @JoinColumn(name="category", referencedColumnName="id_category"))
    private Category category;
    @Column(name = "discount")
    private BigDecimal discount;
    @Column(name = "description")
    private String description;
    @Column(name = "ActualPrice")
    private BigDecimal actualPrice;

public Product(){}

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

//    public Category getCategory() {
//        return Category.valueOf(category);
 //   }

    public Category getCategory() {
        return category;
    }



//    public void setCategory(String category) {
//        this.category = category.toUpperCase();
//    }

    public void setCategory(String category) {
        this.category = Category.valueOf(category);
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(category, product.category) &&
                Objects.equals(discount, product.discount) &&
                Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(),
                getCategory(),
                getDiscount(), getDescription());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                ", actualPrice=" + actualPrice +
                '}';
    }
}