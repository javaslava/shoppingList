package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.console.action.Action;

import java.util.List;

public class ConsoleUIMenu {
    private List<Action> actions;

    public ConsoleUIMenu(List<Action> actions) {
        this.actions = actions;
    }

    public int getConsoleMenuSize() {
        return actions.size();
    }

    public Action getActionName(int response) {
        return actions.get(response);
    }
}
