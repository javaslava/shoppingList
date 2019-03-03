package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CartValidationServiceTest {

    @Mock
    private CartNameLengthValidator cartNameLengthValidator;
    @Mock
    private CartUniqueNameValidator cartUniqueNameValidator;

    @Captor
    private ArgumentCaptor<ShoppingCart> captor;

    private CartValidationService victim;
    private ShoppingCart input = cart();

    @Before
    public void setUp() {
        Set<CartValidationRule> rules = new LinkedHashSet<>();
        rules.add(cartNameLengthValidator);
        rules.add(cartUniqueNameValidator);
        victim = new CartValidationService(rules);
    }

    @Test
    public void shouldValidate() {
        victim.validate(input);
        verify(cartNameLengthValidator).validate(captor.capture());
        verify(cartUniqueNameValidator).validate(captor.capture());
        List<ShoppingCart> resultList = captor.getAllValues();
        resultList.forEach(cart -> assertEquals(input, cart));
    }

    private ShoppingCart cart() {
        ShoppingCart cart = new ShoppingCart();
        cart.setName("TEST-NAME");
        return cart;
    }
}