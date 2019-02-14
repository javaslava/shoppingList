package com.javaguru.shoppinglist.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private String category;
    private BigDecimal discount;
    private String description;
    private BigDecimal minPriceToDiscount = new BigDecimal(20);

    private BigDecimal getMinPriceToDiscount() {
        return minPriceToDiscount;
    }

    public int getMinNameLength() {
        return 3;
    }

    public int getMaxNameLength() {
        return 32;
    }
    public int getMinDescriptionLength() {
        return 10;
    }

    public int getMaxDescriptionLength() {
        return 50;
    }
    public String getMaxDiscountLimit() {
        return "100";
    }

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

    public void setPrice(String price) {
        if (price.equals("")) {
            price = "0";
        }
        if (price.contains(",")) {
            price = price.replace(',', '.');
        }
        this.price = new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public Category getCategory() {
        return Category.valueOf(category);
    }
    public String getCategoryStringName() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category.toUpperCase();
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        if (discount.equals("") || getPrice().compareTo(getMinPriceToDiscount()) < 0) {
            discount = "0";
        }
        if (discount.contains(",")) {
            discount = discount.replace(',', '.');
        }
        this.discount = new BigDecimal(discount).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.equals("")) {
            description = "NO DESCRIPTION";
        }
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
        return Objects.hash(getId(), getName(), getPrice(), getCategory(), getDiscount(), getDescription());
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", category=" + getCategory() +
                ", discount=" + getDiscount() +
                ", description='" + getDescription() + '\'' + '}';
    }
}