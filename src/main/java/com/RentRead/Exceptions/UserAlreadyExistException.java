package com.RentRead.Exceptions;

public class UserAlreadyExistException  extends  RuntimeException{
    public UserAlreadyExistException(String message){
        super(message);
    }
}
