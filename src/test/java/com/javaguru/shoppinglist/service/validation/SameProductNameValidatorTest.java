package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SameProductNameValidatorTest {
    @Mock
    private ProductRepository repo;

    @InjectMocks
    private SameProductNameValidator victim;
    @Rule
    public final ExpectedException expectation = ExpectedException.none();

    @Test
    public void shouldThrowExceptionRepoHasSameProductName() {
        Product fake = createFake();
        when(repo.existsByName(fake)).thenReturn(true);
        expectation.expect(ValidationException.class);
        expectation.expectMessage("Database contains the product with the same name.");
        victim.checkForSameProductName(fake);
    }

    private Product createFake() {
        Product product = new Product();
        product.setName("TEST-NAME");
        product.setPrice(new BigDecimal(30));
        product.setDescription("TEST_DESCRIPTION");
        product.setDiscount(new BigDecimal(50));
        product.setCategory("fruits");
        return product;
    }
}