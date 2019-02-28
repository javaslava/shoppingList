package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.repository.CartManagerRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CartManagerValidatorTest {

    @Mock
    private CartManagerRepository cartManager;

    @InjectMocks
    private CartManagerValidator victim;

    @Rule
    public final ExpectedException expectation = ExpectedException.none();

    @Test
    public void shouldThrowExceptionOverstatedResponse() {
        when(cartManager.getCartManagerSize()).thenReturn(6);
        expectation.expect(ValidationException.class);
        expectation.expectMessage("Not available choice ");
        victim.validate(7);
    }

    @Test
    public void shouldThrowExceptionUnderstatedResponse() {
        when(cartManager.getCartManagerSize()).thenReturn(6);
        expectation.expect(ValidationException.class);
        expectation.expectMessage("Not available choice ");
        victim.validate(0);
    }
    @Test
    public void shouldNotThrowException() {
        when(cartManager.getCartManagerSize()).thenReturn(6);
        try {
            victim.validate(3);
        } catch (Exception ex) {
            assertNull(ex);
        }
    }
}