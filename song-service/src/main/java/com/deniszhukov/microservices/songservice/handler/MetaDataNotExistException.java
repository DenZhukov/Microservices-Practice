package com.deniszhukov.microservices.songservice.handler;

public class MetaDataNotExistException extends RuntimeException {
    public MetaDataNotExistException(String message) {
        super(message);
    }
}
