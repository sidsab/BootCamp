package com.example.bootcamp.exceptions;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(String message)
    {
        super(message);
    }
}
