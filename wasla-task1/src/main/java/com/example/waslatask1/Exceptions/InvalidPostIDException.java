package com.example.waslatask1.Exceptions;

public class InvalidPostIDException extends RuntimeException{
    public InvalidPostIDException(){
        super("Invalid Post ID");
    }
}
