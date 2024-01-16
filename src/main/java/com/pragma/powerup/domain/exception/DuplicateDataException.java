package com.pragma.powerup.domain.exception;

public class DuplicateDataException extends RuntimeException{

    public DuplicateDataException(String message) {
        super(message);
    }

}