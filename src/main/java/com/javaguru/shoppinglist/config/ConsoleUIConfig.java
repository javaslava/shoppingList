package com.javaguru.shoppinglist.config;

import com.javaguru.shoppinglist.console.action.Action;
import com.javaguru.shoppinglist.repository.ConsoleUIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConsoleUIConfig {
    private final Action createProductAction;
    private final Action findProductByIdAction;
    private final Action createCartAction;
    private final Action manageCartAction;
    private final Action exitAction;

    @Autowired
    public ConsoleUIConfig(Action createProductAction, Action findProductByIdAction, Action createCartAction,
                           Action manageCartAction, Action exitAction) {
        this.createProductAction = createProductAction;
        this.findProductByIdAction = findProductByIdAction;
        this.createCartAction = createCartAction;
        this.manageCartAction = manageCartAction;
        this.exitAction = exitAction;
    }

    @Bean
    public ConsoleUIRepository consoleUIrepository() {
        List<Action> actions = new ArrayList<>();
        actions.add(createProductAction);
        actions.add(findProductByIdAction);
        actions.add(createCartAction);
        actions.add(manageCartAction);
        actions.add(exitAction);
        return new ConsoleUIRepository(actions);
    }
}
