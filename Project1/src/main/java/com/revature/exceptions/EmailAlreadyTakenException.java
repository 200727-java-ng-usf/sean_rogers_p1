package com.revature.exceptions;

public class EmailAlreadyTakenException extends RuntimeException{

    public EmailAlreadyTakenException() {
        super("Email already associated with an existing user");
    }

}
