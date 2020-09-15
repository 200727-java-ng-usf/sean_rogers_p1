package com.revature.exceptions;

/**
 * thrown when administrator tries to update a user with a username that doesn't exist
 */
public class UsernameNotFoundException extends RuntimeException {

    public UsernameNotFoundException() {
        super("Username not found");
    }

}
