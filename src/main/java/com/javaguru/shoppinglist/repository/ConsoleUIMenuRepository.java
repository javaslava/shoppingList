package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.console.*;
import com.javaguru.shoppinglist.service.*;

import java.util.ArrayList;
import java.util.List;

public class ConsoleUIMenuRepository {

    private List<Action> actions = new ArrayList<>();
    private final ProductService productService;
    private final CartService cartService;

    public ConsoleUIMenuRepository(ProductService service, CartService shoppingCartService) {
        this.productService = service;
        this.cartService = shoppingCartService;
        actions.add(new CreateProductAction(productService));
        actions.add(new FindProductByIdAction(productService));
        actions.add(new CreateCartAction(cartService));
        actions.add(new ManageShoppingCartAction(cartService, productService));
        actions.add(new ExitAction());
    }

    public Action chooseAction(int response) {
        return actions.get(response);
    }

    public int getConsoleMenuSize() {
        return actions.size();
    }

    public Action getActionName(int response) {
        return actions.get(response);
    }
}
