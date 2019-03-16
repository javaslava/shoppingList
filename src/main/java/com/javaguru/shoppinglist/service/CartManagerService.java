package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.repository.CartManagerRepository;
import com.javaguru.shoppinglist.service.validation.CartManagerValidation.CartManagerValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartManagerService {

    private final CartManagerRepository cartManagerRepo;
    private final CartManagerValidationService validationService;

    @Autowired
    public CartManagerService(CartManagerRepository cartManagerRepo,
                              CartManagerValidationService validationService) {
        this.cartManagerRepo = cartManagerRepo;
        this.validationService = validationService;
    }

    public int getCartManagerSize() {
        return cartManagerRepo.getCartManagerSize();
    }

    public void printCartManagerMenu(String cartName) {
        cartManagerRepo.printCartManagerMenu(cartName);
    }

    public void runCartManagerMenuChoice(int response, String cartName) {
        validationService.validate(response);
        cartManagerRepo.runCartManagerMenuChoice(response, cartName);
    }
}
