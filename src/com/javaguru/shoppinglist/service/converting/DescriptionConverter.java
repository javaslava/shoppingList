package com.javaguru.shoppinglist.service.converting;

public class DescriptionConverter {

    public String descriptionFilter(String description) {
        if (description.equals("")) {
            description = "NO DESCRIPTION";
        }
        return description;
    }
}
