package com.RentRead.Services;

import com.RentRead.Exceptions.BookNotFoundException;
import com.RentRead.Exchange.Request.BookRequest;
import com.RentRead.Models.BookStore;

import java.util.List;

public interface IBookService {
    BookStore createBook(BookRequest request);

    BookStore updateBook(Long id, BookRequest request) throws BookNotFoundException;

    BookStore removeBook(Long id) throws BookNotFoundException;

    List<BookStore> getAllBooks();

    BookStore getBookById(Long id) throws BookNotFoundException;
}
