package com.revature.exceptions;

/**
 * Thrown when user tries to navigate to a page the user doesn't belong to
 */
public class NotAuthorizedException extends RuntimeException{

    public NotAuthorizedException() {
        super("User does not have administrator privileges");
    }

}
