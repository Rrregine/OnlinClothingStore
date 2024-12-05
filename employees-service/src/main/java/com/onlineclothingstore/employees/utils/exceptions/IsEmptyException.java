package com.onlineclothingstore.employees.utils.exceptions;

public class IsEmptyException extends RuntimeException{

    public IsEmptyException() {}

    public IsEmptyException(String message) { super(message); }

   // public IsEmptyException(Throwable cause) { super(cause); }

    public IsEmptyException(String message, Throwable cause) { super(message, cause); }
}
