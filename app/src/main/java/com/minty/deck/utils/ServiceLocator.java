package com.minty.deck.utils;

import com.minty.deck.dao.UserDao;

public class ServiceLocator {

    private static ServiceLocator instance = null;

    private ServiceLocator() {}

    public static ServiceLocator getInstance() {
        if (instance == null) {
            synchronized(ServiceLocator.class) {
                instance = new ServiceLocator();
            }
        }
        return instance;
    }

    public Navigator getNavigator() {
        return new Navigator();
    }

    public UserDao getUserDao() {
        return new UserDao();
    }
}
