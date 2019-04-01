package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.InMemoryProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.math.BigDecimal;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private InMemoryProductRepository productRepo;

    @InjectMocks
    private ProductService victim;
    private Product product = product();

    @Test
    public void shouldTestFindProductById() {
        when(productRepo.findProductById(0L)).thenReturn(Optional.ofNullable(product()));
        Product result = victim.findProductById(0L);
        assertThat(result).isEqualTo(product());
    }

    @Test
    public void shouldTestDescriptionFilter() {
        String description = "";
        String returnedDescription = victim.descriptionFilter(description);
        assertEquals("NO DESCRIPTION", returnedDescription);
    }

    @Test
    public void shouldTestActualPriceCalculator() {
        BigDecimal actualPrice = victim.actualPriceCalculator(product.getPrice(), product.getDiscount());
        assertEquals("15.00", actualPrice.toString());
    }

    @Test
    public void shouldTestDiscountFilterEmptyDiscount() {
        BigDecimal returnedDiscount = victim.discountFilter("", new BigDecimal("25.00"));
        assertEquals("0.00", returnedDiscount.toString());
    }

    @Test
    public void shouldTestDiscountFilterLowPrice() {
        BigDecimal returnedDiscount = victim.discountFilter("50.00", new BigDecimal("15.00"));
        assertEquals("0.00", returnedDiscount.toString());
    }

    @Test
    public void shouldTestDiscountFilterNumberFormat() {
        BigDecimal returnedDiscount = victim.discountFilter("50,00", new BigDecimal("30.00"));
        assertEquals("50.00", returnedDiscount.toString());
    }

    @Test
    public void shouldTestPriceFilterEmptyPrice() {
        BigDecimal returnedPrice = victim.priceFilter("");
        assertEquals("0.00", returnedPrice.toString());
    }

    @Test
    public void shouldTestPriceFilterNumberFormat() {
        BigDecimal returnedPrice = victim.priceFilter("50,00");
        assertEquals("50.00", returnedPrice.toString());
    }

    private Product product() {
        Product product = new Product();
        product.setName("TEST-NAME");
        product.setPrice(new BigDecimal(30));
        product.setDescription("TEST_DESCRIPTION");
        product.setDiscount(new BigDecimal(50));
        product.setCategory("fruits");
        product.setId(0L);
        return product;
    }

    private Product expectedProduct() {
        Product product = new Product();
        product.setName("TEST-NAME");
        product.setPrice(new BigDecimal(30));
        product.setDescription("TEST_DESCRIPTION");
        product.setDiscount(new BigDecimal(50));
        product.setCategory("fruits");
        product.setId(0L);
        return product;
    }
}