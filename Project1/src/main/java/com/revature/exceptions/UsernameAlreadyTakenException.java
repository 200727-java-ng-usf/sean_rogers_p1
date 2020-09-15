package com.revature.exceptions;

/**
 * thrown when user tries to register with a username that already exists
 */
public class UsernameAlreadyTakenException extends RuntimeException{

    public UsernameAlreadyTakenException() {
        super("Username already taken");
    }

}
