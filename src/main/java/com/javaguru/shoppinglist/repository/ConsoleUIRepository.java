package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.console.action.Action;

import java.util.List;

public class ConsoleUIRepository {
    private List<Action> actions;

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
