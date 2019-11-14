package com.tavisca.training.servers;

import java.io.IOException;

public class RMIImplementation implements RMI {
    @Override
    public String process(Item item) throws IOException {

        System.out.println("Item with id " + item.getId() + " and name " + item.getName() + " is processed");
        return null;
    }

    @Override
    public String delete(Item item) throws IOException {
        System.out.println("Item with id " + item.getId() + " and name " + item.getName() + " is deleted");
        return null;
    }
}
