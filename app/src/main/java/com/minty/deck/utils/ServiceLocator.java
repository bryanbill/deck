package com.minty.deck.utils;

public class ServiceLocator {
    private static ServiceLocator instance;

    private ServiceLocator() {
    }

    public static ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    public Navigator getNavigator() {
        return new Navigator();
    }
}
