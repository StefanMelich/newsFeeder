package com.example.feeder.domain.exception;

public class ErrorMessage {

    private final String errorMessage;

    public ErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
