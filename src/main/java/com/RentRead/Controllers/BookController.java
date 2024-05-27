package com.RentRead.Controllers;

import com.RentRead.Exceptions.BookNotFoundException;
import com.RentRead.Exchange.Request.BookRequest;
import com.RentRead.Models.BookStore;
import com.RentRead.Services.IBookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    private final IBookService bookService;

    @Autowired
    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookStore> createBook(@Valid @RequestBody BookRequest bookRequest) {
        log.info("Creating book: {}", bookRequest);
        BookStore newBook = bookService.createBook(bookRequest);
        if (newBook == null) {
            log.warn("Book already exists with title: {}", bookRequest.getTitle());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        log.info("Book created successfully: {}", newBook);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookStore> updateBook(@PathVariable("id") Long id, @Valid @RequestBody BookRequest request)
            throws BookNotFoundException {
        log.info("Updating book with ID: {}", id);
        BookStore updatedBook = bookService.updateBook(id, request);
        log.info("Book updated successfully with ID: {}", id);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeBook(@PathVariable("id") Long id) throws BookNotFoundException {
        log.info("Deleting book with ID: {}", id);
        bookService.removeBook(id);
        log.info("Book deleted successfully with ID: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<BookStore>> getAllBooks() {
        log.info("Fetching all books");
        List<BookStore> books = bookService.getAllBooks();
        log.info("Books fetched successfully");
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookStore> getBookById(@PathVariable("id") Long id) throws BookNotFoundException {
        log.info("Fetching book with ID: {}", id);
        BookStore book = bookService.getBookById(id);
        log.info("Book fetched successfully with ID: {}", id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
