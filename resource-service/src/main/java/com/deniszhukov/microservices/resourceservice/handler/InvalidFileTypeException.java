package com.deniszhukov.microservices.resourceservice.handler;

public class InvalidFileTypeException extends RuntimeException {
    public InvalidFileTypeException(String message) {
        super(message);
    }
}
