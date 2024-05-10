package com.example.bootcamp.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String message)
    {
        super(message);
    }
}
