package com.javaguru.shoppinglist.service.converting;

import java.math.BigDecimal;

public class ActualPriceConverter {
    private final BigDecimal bd1 = BigDecimal.valueOf(1);
    private final BigDecimal bd100 = BigDecimal.valueOf(100);

    public BigDecimal actualPriceCalculator(BigDecimal price, BigDecimal discount) {
        BigDecimal discountFactor = discount.divide(bd100);
        discountFactor = bd1.subtract(discountFactor);
        return price.multiply(discountFactor).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
