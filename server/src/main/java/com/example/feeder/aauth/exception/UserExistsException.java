package com.example.feeder.aauth.exception;

public class UserExistsException extends RuntimeException {

    private final String message;

    public UserExistsException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
