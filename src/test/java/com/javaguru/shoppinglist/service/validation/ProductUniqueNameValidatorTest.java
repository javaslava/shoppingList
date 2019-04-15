package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidation.ProductUniqueNameValidationRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductUniqueNameValidatorTest {
    @Mock
    private ProductRepository repo;

    @InjectMocks
    private ProductUniqueNameValidationRule victim;

    @Test
    public void shouldThrowExceptionRepoHasSameProductName() {
        Product fake = createFake();
        when(repo.existsByName(fake)).thenReturn(true);
        assertThatThrownBy(() -> victim.validate(createFake()))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Database contains the product with the same name.");
    }

    private Product createFake() {
        Product product = new Product();
        product.setName("TEST-NAME");
        product.setPrice(new BigDecimal(30));
        product.setDescription("TEST_DESCRIPTION");
        product.setDiscount(new BigDecimal(50));
        product.setCategory("FRUITS");
        return product;
    }
}