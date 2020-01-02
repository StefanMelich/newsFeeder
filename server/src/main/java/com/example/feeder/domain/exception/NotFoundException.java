package com.example.feeder.domain.exception;

public class NotFoundException extends RuntimeException {

    private final Long id;

    public NotFoundException(Long id) {
        super(String.format("Entity with id [%d] not found", id));
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
