package com.javaguru.shoppinglist.service.converting;

import java.math.BigDecimal;

public class PriceConverter {

    public BigDecimal priceFilter(String price) {
        if (price.equals("")) {
            price = "0";
        }
        if (price.contains(",")) {
            price = price.replace(',', '.');
        }
        return new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
