package com.revature.exceptions;


public class UsernameNotFoundException extends RuntimeException {

    public UsernameNotFoundException() {
        super("Username not found");
    }

}
