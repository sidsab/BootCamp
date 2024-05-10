package com.example.bootcamp.exceptions;

public class DepartmentIsReadOnlyException extends RuntimeException{
    public DepartmentIsReadOnlyException(String message)
    {
        super(message);
    }
}
