package com.RentRead.Exceptions;

public class BookIsAlreadyRentedOutException extends RuntimeException {
    public BookIsAlreadyRentedOutException(String message){
        super(message);
    }

}
