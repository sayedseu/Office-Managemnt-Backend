package com.example.oficemanagement.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String value) {
        super("Not Found " + value);
    }
}
