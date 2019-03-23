package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.InMemoryCartRepository;
import com.javaguru.shoppinglist.service.validation.CartValidation.CartUniqueNameValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartUniqueNameValidatorTest {

    @Mock
    private InMemoryCartRepository repo;

    @InjectMocks
    private CartUniqueNameValidator victim;

    @Rule
    public final ExpectedException expectation = ExpectedException.none();

    @Test
    public void shouldThrowException() {
        ShoppingCart cart = createFake("cart");
        when(repo.checkForCartByName(cart.getName())).thenReturn(true);
        expectation.expect(ValidationException.class);
        expectation.expectMessage("Database contains the cart with the same name.");
        victim.validate(cart);
    }

    private ShoppingCart createFake(String name) {
        ShoppingCart cart = new ShoppingCart();
        cart.setName(name);
        return cart;
    }
}