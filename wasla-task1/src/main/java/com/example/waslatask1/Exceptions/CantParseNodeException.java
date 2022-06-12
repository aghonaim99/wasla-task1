package com.example.waslatask1.Exceptions;

public class CantParseNodeException extends RuntimeException {
    public CantParseNodeException(String nodename, String exMsg)
    {
        super("Error parsing node: " + nodename + " --- " + exMsg);
    }
}