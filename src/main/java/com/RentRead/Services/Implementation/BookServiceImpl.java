package com.RentRead.Services.Implementation;

import com.RentRead.Exceptions.BookNotFoundException;
import com.RentRead.Exchange.Request.BookRequest;
import com.RentRead.Models.BookStore;
import com.RentRead.Repository.BookRepository;
import com.RentRead.Services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookRepository BookRepository;
    @Override
    public BookStore createBook(BookRequest request) {
        if (BookRepository.existsByTitle(request.getTitle())) {
            return null;
        }
        BookStore newBook = BookStore
                .builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .genre(request.getGenre())
                .available(request.getAvailable())
                .build();

        return BookRepository.save(newBook);
    }

    @Override
    public BookStore updateBook(Long id, BookRequest request) throws BookNotFoundException {
        BookStore book = getBookById(id);
        if (request.getTitle() != null) {
            book.setTitle(request.getTitle());
        }
        if (request.getAuthor() != null) {
            book.setAuthor(request.getAuthor());
        }
        if (request.getGenre() != null) {
            book.setGenre(request.getGenre());
        }
        return BookRepository.save(book);
    }

    @Override
    public BookStore removeBook(Long id) throws BookNotFoundException {
        BookStore book = getBookById(id);
        BookRepository.delete(book);
        return book;
    }

    @Override
    public List<BookStore> getAllBooks() {
        return BookRepository.findAll();
    }

    @Override
    public BookStore getBookById(Long id) throws BookNotFoundException {
        return BookRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book Not Found"));
    }
}
