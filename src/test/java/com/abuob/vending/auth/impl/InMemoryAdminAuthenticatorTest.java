package com.abuob.vending.auth.impl;

import com.abuob.vending.auth.AdminAuthenticator;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryAdminAuthenticatorTest {
    private final String USERNAME = "username";
    private final String PASSWORD = "password123";

    private AdminAuthenticator adminAuthenticator;
    private Map<String, String> usernamePasswordMap = new HashMap<>();

    @Before
    public void setup() {
        usernamePasswordMap.put(USERNAME, PASSWORD);
    }

    @Test
    public void isValid_nullInput() {
        adminAuthenticator = new InMemoryAdminAuthenticator(usernamePasswordMap);
        assertThat(adminAuthenticator.isValid(null, null)).isFalse();
    }

    @Test
    public void isValid_nullUsername() {
        adminAuthenticator = new InMemoryAdminAuthenticator(usernamePasswordMap);
        assertThat(adminAuthenticator.isValid(null, PASSWORD)).isFalse();
    }

    @Test
    public void isValid_nullPassword() {
        adminAuthenticator = new InMemoryAdminAuthenticator(usernamePasswordMap);
        assertThat(adminAuthenticator.isValid(USERNAME, null)).isFalse();
    }

    @Test
    public void isValid_nullSuccess() {
        adminAuthenticator = new InMemoryAdminAuthenticator(usernamePasswordMap);
        assertThat(adminAuthenticator.isValid(USERNAME, PASSWORD)).isTrue();
    }

    @Test
    public void isValid_unknownUser() {
        adminAuthenticator = new InMemoryAdminAuthenticator(usernamePasswordMap);
        assertThat(adminAuthenticator.isValid("otherUser", "somePassword")).isFalse();
    }
}
