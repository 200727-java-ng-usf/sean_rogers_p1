package com.revature.exceptions;

public class NotAuthorizedException extends RuntimeException{

    public NotAuthorizedException() {
        super("User does not have administrator privileges");
    }

}
