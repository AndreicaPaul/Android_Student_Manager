package com.example.paul.androidstudentmanager.model;

/**
 * Created by Paul on 12-Feb-17.
 */

public class StringContainsNumbersException extends Exception {
    //Parameterless Constructor
    public StringContainsNumbersException() {}

    //Constructor that accepts a message
    public StringContainsNumbersException(String message)
    {
        super(message);
    }
}
