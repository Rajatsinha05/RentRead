package com.RentRead.Exceptions;


import com.RentRead.Exchange.Response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Response> userAlreadyExistException(UserAlreadyExistException e) {
        Response response = new Response(e.getMessage(), HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Response> bookNotFoundException(BookNotFoundException e) {
        Response response = new Response(e.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response> userNotFoundException(UserNotFoundException e) {
        Response response = new Response(e.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(NotAbleToRentException.class)
    public ResponseEntity<Response> notAbleToRentException(NotAbleToRentException e) {
        Response response = new Response(e.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
