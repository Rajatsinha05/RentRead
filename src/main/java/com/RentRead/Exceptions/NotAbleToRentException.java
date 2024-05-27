package com.RentRead.Exceptions;

public class NotAbleToRentException extends RuntimeException {
    public NotAbleToRentException(String message){
        super(message);
    }
}
