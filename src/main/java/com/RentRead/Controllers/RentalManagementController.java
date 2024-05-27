package com.RentRead.Controllers;

import com.RentRead.Exceptions.BookIsAlreadyRentedOutException;
import com.RentRead.Exceptions.BookNotFoundException;
import com.RentRead.Exceptions.NotAbleToRentException;
import com.RentRead.Exceptions.UserNotFoundException;
import com.RentRead.Exchange.Request.RentBookRequest;
import com.RentRead.Exchange.Response.RentBookResponse;
import com.RentRead.Services.IRentalManagementService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@Slf4j
public class RentalManagementController {

    private final IRentalManagementService rentalManagementService;

    @Autowired
    public RentalManagementController(IRentalManagementService rentalManagementService) {
        this.rentalManagementService = rentalManagementService;
    }

    @PostMapping("/rent/{id}")
    public ResponseEntity<RentBookResponse> rentBook(@PathVariable Long id, @Valid @RequestBody RentBookRequest request) {
        log.info("Renting book with ID: {} for user ID: {}", id, request.getUserId());
        try {
            RentBookResponse response = rentalManagementService.rentBook(id, request);
            log.info("Book rented successfully for user ID: {}", request.getUserId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BookNotFoundException | BookIsAlreadyRentedOutException | NotAbleToRentException | UserNotFoundException e) {
            log.error("Error renting book: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/return/{id}")
    public ResponseEntity<RentBookResponse> returnBook(@PathVariable Long id, @Valid @RequestBody RentBookRequest request) {
        log.info("Returning book with ID: {} for user ID: {}", id, request.getUserId());
        try {
            RentBookResponse response = rentalManagementService.returnBook(id, request);
            log.info("Book returned successfully for user ID: {}", request.getUserId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserNotFoundException | BookNotFoundException e) {
            log.error("Error returning book: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
