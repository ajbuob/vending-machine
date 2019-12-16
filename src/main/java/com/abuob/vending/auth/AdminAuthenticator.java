package com.abuob.vending.auth;

public interface AdminAuthenticator {

    /**
     * Method to authenticate admin users
     *
     * @param username
     * @param password
     * @return indicator of the username/password combination validity
     */
    Boolean isValid(String username, String password);
}
