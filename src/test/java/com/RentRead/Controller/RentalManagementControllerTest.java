package com.RentRead.Controller;


import com.RentRead.Controllers.RentalManagementController;
import com.RentRead.Exceptions.BookIsAlreadyRentedOutException;
import com.RentRead.Exceptions.BookNotFoundException;
import com.RentRead.Exceptions.NotAbleToRentException;
import com.RentRead.Exceptions.UserNotFoundException;
import com.RentRead.Exchange.Request.RentBookRequest;
import com.RentRead.Exchange.Response.RentBookResponse;
import com.RentRead.Services.IRentalManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RentalManagementControllerTest {

    @Mock
    private IRentalManagementService rentalManagementService;

    @InjectMocks
    private RentalManagementController rentalManagementController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void rentBook_success() throws BookNotFoundException, BookIsAlreadyRentedOutException, NotAbleToRentException, UserNotFoundException {
        Long bookId = 1L;
        RentBookRequest request = new RentBookRequest(1L);
        RentBookResponse response = new RentBookResponse();

        when(rentalManagementService.rentBook(anyLong(), any(RentBookRequest.class))).thenReturn(response);

        ResponseEntity<RentBookResponse> result = rentalManagementController.rentBook(bookId, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(rentalManagementService, times(1)).rentBook(bookId, request);
    }

    @Test
    void rentBook_bookNotFound() throws BookNotFoundException, BookIsAlreadyRentedOutException, NotAbleToRentException, UserNotFoundException {
        Long bookId = 1L;
        RentBookRequest request = new RentBookRequest(1L);

        doThrow(new BookNotFoundException("Book is not present")).when(rentalManagementService).rentBook(anyLong(), any(RentBookRequest.class));

        ResponseEntity<RentBookResponse> result = rentalManagementController.rentBook(bookId, request);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(rentalManagementService, times(1)).rentBook(bookId, request);
    }

    @Test
    void returnBook_success() throws UserNotFoundException, BookNotFoundException {
        Long bookId = 1L;
        RentBookRequest request = new RentBookRequest(1L);
        RentBookResponse response = new RentBookResponse();

        when(rentalManagementService.returnBook(anyLong(), any(RentBookRequest.class))).thenReturn(response);

        ResponseEntity<RentBookResponse> result = rentalManagementController.returnBook(bookId, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(rentalManagementService, times(1)).returnBook(bookId, request);
    }

    @Test
    void returnBook_userNotFound() throws UserNotFoundException, BookNotFoundException {
        Long bookId = 1L;
        RentBookRequest request = new RentBookRequest(1L);

        doThrow(new UserNotFoundException("User is not found")).when(rentalManagementService).returnBook(anyLong(), any(RentBookRequest.class));

        ResponseEntity<RentBookResponse> result = rentalManagementController.returnBook(bookId, request);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(rentalManagementService, times(1)).returnBook(bookId, request);
    }
}
