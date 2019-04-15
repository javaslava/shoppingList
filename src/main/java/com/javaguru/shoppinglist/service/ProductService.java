package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidation.ProductValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepo;
    private final ProductValidationService validationService;

    @Autowired
    public ProductService(ProductRepository productRepo, ProductValidationService validationService) {
        this.productRepo = productRepo;
        this.validationService = validationService;
    }

    private final BigDecimal MIN_PRICE_TO_DISCOUNT = new BigDecimal(20);
    private final BigDecimal bd1 = BigDecimal.valueOf(1);
    private final BigDecimal bd100 = BigDecimal.valueOf(100);

    @Transactional
    public Long createProduct(String name, String price, String description, String discount, String category) {
        Product product = new Product();
        BigDecimal productPrice = priceFilter(price);
        String productDescription = descriptionFilter(description);
        BigDecimal productDiscount = discountFilter(discount, productPrice);
        BigDecimal actualProductPrice = actualPriceCalculator(productPrice, productDiscount);
        product.setName(name);
        product.setPrice(productPrice);
        product.setDescription(productDescription);
        product.setDiscount(productDiscount);
        product.setActualPrice(actualProductPrice);
        product.setCategory(category);
        validationService.validate(product);
        return productRepo.insert(product);
    }

    public Product findProductById(Long id) {
        return productRepo.findProductById(id).orElseThrow(()
                -> new IllegalArgumentException("Product with specified" + " id " + id + " not found"));
    }

    public Optional<Product> getProductByName(String productName) {
        return productRepo.getProductByName(productName);
    }

    private BigDecimal priceFilter(String price) {
        if (price.equals("")) {
            price = "0";
        }
        if (price.contains(",")) {
            price = price.replace(',', '.');
        }
        return new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal discountFilter(String discount, BigDecimal price) {
        if (discount.equals("") || price.compareTo(MIN_PRICE_TO_DISCOUNT) < 0) {
            discount = "0";
        }
        if (discount.contains(",")) {
            discount = discount.replace(',', '.');
        }
        return new BigDecimal(discount).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private String descriptionFilter(String description) {
        if (description.equals("")) {
            description = "NO DESCRIPTION";
        }
        return description;
    }

    private BigDecimal actualPriceCalculator(BigDecimal price, BigDecimal discount) {
        BigDecimal discountFactor = discount.divide(bd100);
        discountFactor = bd1.subtract(discountFactor);
        return price.multiply(discountFactor).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
