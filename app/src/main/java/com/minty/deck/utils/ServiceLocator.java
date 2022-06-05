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

    public void init(App app) {
        app.getAppComponent().inject(this);
    }

    public Navigator getNavigator() {
        return new Navigator();
    }
}
