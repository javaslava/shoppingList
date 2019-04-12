package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidation.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductValidationServiceTest {

    @Mock
    private ProductUniqueNameValidationRule productUniqueName;
    @Mock
    private ProductNameNotNullValidationRule productNameNotNull;
    @Mock
    private ProductNameLengthValidationRule productNameLength;
    @Mock
    private ProductPriceValidationRule productPrice;
    @Mock
    private ProductDiscountValidationRule productDiscount;
    @Mock
    private ProductDescriptionValidationRule productDescription;
    @Mock
    private ProductCategoryNameValidationRule productCategoryName;

    @Captor
    private ArgumentCaptor<Product> captor;

    private ProductValidationService victim;
    private Product input = product();

    @Before
    public void setUp() {
        Set<ProductValidationRule> rules = new LinkedHashSet<>();
        rules.add(productUniqueName);
        rules.add(productNameNotNull);
        rules.add(productNameLength);
        rules.add(productPrice);
        rules.add(productDiscount);
        rules.add(productDescription);
        rules.add(productCategoryName);
        victim = new ProductValidationService(rules);
    }

    @Test
    public void shouldValidate() {
        victim.validate(input);
        verify(productUniqueName).validate(captor.capture());
        verify(productNameNotNull).validate(captor.capture());
        verify(productNameLength).validate(captor.capture());
        verify(productPrice).validate(captor.capture());
        verify(productDiscount).validate(captor.capture());
        verify(productDescription).validate(captor.capture());
        verify(productCategoryName).validate(captor.capture());
        List<Product> resultList = captor.getAllValues();
        resultList.forEach(product -> assertEquals(input, product));
    }

    private Product product() {
        Product product = new Product();
        product.setName("TEST-NAME");
        product.setPrice(new BigDecimal(30));
        product.setActualPrice(new BigDecimal(30));
        product.setDescription("TEST_DESCRIPTION");
        product.setDiscount(new BigDecimal(50));
        product.setCategory("FRUITS");
        product.setId(102L);
        return product;
    }
}