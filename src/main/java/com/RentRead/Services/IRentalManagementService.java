package com.RentRead.Services;

import com.RentRead.Exceptions.BookIsAlreadyRentedOutException;
import com.RentRead.Exceptions.BookNotFoundException;
import com.RentRead.Exceptions.NotAbleToRentException;
import com.RentRead.Exceptions.UserNotFoundException;
import com.RentRead.Exchange.Request.RentBookRequest;
import com.RentRead.Exchange.Response.RentBookResponse;

public interface IRentalManagementService {
    RentBookResponse rentBook(Long id, RentBookRequest request) throws BookNotFoundException, BookIsAlreadyRentedOutException, NotAbleToRentException, UserNotFoundException;
    RentBookResponse returnBook(Long id, RentBookRequest request) throws UserNotFoundException, BookNotFoundException;
}
