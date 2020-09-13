package com.revature.exceptions;

public class UsernamePasswordMismatchException extends RuntimeException {

    public UsernamePasswordMismatchException() {
        super("Username or password incorrect");
    }

}
