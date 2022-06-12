package com.example.waslatask1.Exceptions;

public class InaccessibleFileException extends RuntimeException{
    public InaccessibleFileException(String filename){
        super("Can't access file specified: " + filename);
    }
}
