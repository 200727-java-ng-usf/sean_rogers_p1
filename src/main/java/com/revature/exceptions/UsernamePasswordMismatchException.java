package com.revature.exceptions;

/**
 * Thrown when a user mistypes a username/password pair, or enters a username that doesn't exist, or enters a username
 * that does exist but is inactive
 */
public class UsernamePasswordMismatchException extends RuntimeException {

    public UsernamePasswordMismatchException() {
        super("Username or password incorrect");
    }

}
