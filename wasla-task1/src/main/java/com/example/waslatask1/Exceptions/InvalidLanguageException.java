package com.example.waslatask1.Exceptions;

public class InvalidLanguageException extends RuntimeException{
    public InvalidLanguageException(){
        super("Invalid language");
    }
}
