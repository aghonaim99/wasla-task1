package com.example.waslatask1.Exceptions;

public class InvalidCategoryIDException extends RuntimeException{
    public InvalidCategoryIDException()
    {
        super("Invalid Category ID");
    }
}
