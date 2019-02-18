package com.javaguru.shoppinglist.service.converting;

import java.math.BigDecimal;

public class DiscountConverter {
    private final BigDecimal MIN_PRICE_TO_DISCOUNT = new BigDecimal(20);

    public BigDecimal discountFilter(String discount, BigDecimal price) {
        if (discount.equals("") || price.compareTo(MIN_PRICE_TO_DISCOUNT) < 0) {
            discount = "0";
        }
        if (discount.contains(",")) {
            discount = discount.replace(',', '.');
        }
        return new BigDecimal(discount).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
