package com.example.oficemanagement.exception;

public class ResourceAlreadyExistException extends Exception {
    public ResourceAlreadyExistException(String data) {
        super("Not Exist " + data);
    }
}
