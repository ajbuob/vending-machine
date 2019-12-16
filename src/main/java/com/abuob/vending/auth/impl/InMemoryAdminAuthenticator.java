package com.abuob.vending.auth.impl;

import com.abuob.vending.auth.AdminAuthenticator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InMemoryAdminAuthenticator implements AdminAuthenticator {

    private Map<String, String> usernamePasswordMap = new HashMap<>();

    public InMemoryAdminAuthenticator(Map<String, String> usernamePasswordMap) {
        usernamePasswordMap.forEach((username, password) -> this.usernamePasswordMap.put(username, password));
    }

    @Override
    public Boolean isValid(String username, String password) {
        if (Objects.isNull(username) || Objects.isNull(password)) {
            return Boolean.FALSE;
        }
        return usernamePasswordMap.containsKey(username) && usernamePasswordMap.get(username).equals(password);
    }
}
