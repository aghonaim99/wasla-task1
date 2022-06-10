package com.example.waslatask1.Exceptions;

public class PostAlreadyExistsException extends RuntimeException{
    public PostAlreadyExistsException(){
        super("Post already exists");
    }
}
