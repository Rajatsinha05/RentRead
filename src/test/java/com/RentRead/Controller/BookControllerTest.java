package com.RentRead.Controller;


import com.RentRead.Controllers.BookController;
import com.RentRead.Exceptions.BookNotFoundException;
import com.RentRead.Exchange.Request.BookRequest;
import com.RentRead.Models.BookStore;
import com.RentRead.Services.IBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private IBookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBook_success() {
        BookRequest request = new BookRequest();
        BookStore book = new BookStore();

        when(bookService.createBook(any(BookRequest.class))).thenReturn(book);

        ResponseEntity<BookStore> response = bookController.createBook(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(book, response.getBody());
        verify(bookService, times(1)).createBook(request);
    }

    @Test
    void createBook_conflict() {
        BookRequest request = new BookRequest();

        when(bookService.createBook(any(BookRequest.class))).thenReturn(null);

        ResponseEntity<BookStore> response = bookController.createBook(request);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        verify(bookService, times(1)).createBook(request);
    }

    @Test
    void updateBook_success() throws BookNotFoundException {
        Long bookId = 1L;
        BookRequest request = new BookRequest();
        BookStore book = new BookStore();

        when(bookService.updateBook(anyLong(), any(BookRequest.class))).thenReturn(book);

        ResponseEntity<BookStore> response = bookController.updateBook(bookId, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
        verify(bookService, times(1)).updateBook(bookId, request);
    }




    @Test
    void getAllBooks_success() {
        List<BookStore> books = new ArrayList<>();

        when(bookService.getAllBooks()).thenReturn(books);

        ResponseEntity<List<BookStore>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(books, response.getBody());
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void getBookById_success() throws BookNotFoundException {
        Long bookId = 1L;
        BookStore book = new BookStore();

        when(bookService.getBookById(anyLong())).thenReturn(book);

        ResponseEntity<BookStore> response = bookController.getBookById(bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
        verify(bookService, times(1)).getBookById(bookId);
    }


}
