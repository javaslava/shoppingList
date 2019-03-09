package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.console.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsoleUIRepository {
    private List<Action> actions;

    @Autowired
    public ConsoleUIRepository(List<Action> actions) {
        this.actions = actions;
    }

    public int getConsoleMenuSize() {
        return actions.size();
    }

    public Action getActionName(int response) {
        return actions.get(response);
    }
}
