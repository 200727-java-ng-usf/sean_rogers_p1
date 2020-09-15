package com.revature.exceptions;

/**
 * Thrown when user tries to register with an email that already exists in the database
 */
public class EmailAlreadyTakenException extends RuntimeException{

    public EmailAlreadyTakenException() {
        super("Email already associated with an existing user");
    }

}
