package com.RentRead.Services.Implementation;

import com.RentRead.Exceptions.BookIsAlreadyRentedOutException;
import com.RentRead.Exceptions.BookNotFoundException;
import com.RentRead.Exceptions.NotAbleToRentException;
import com.RentRead.Exceptions.UserNotFoundException;
import com.RentRead.Exchange.Request.RentBookRequest;
import com.RentRead.Exchange.Response.RentBookResponse;
import com.RentRead.Models.BookStore;
import com.RentRead.Models.User;
import com.RentRead.Repository.BookRepository;
import com.RentRead.Repository.UserRepository;
import com.RentRead.Services.IRentalManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RentalManagementServiceImpl implements IRentalManagementService {
    @Autowired
    private BookRepository BookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RentBookResponse rentBook(Long id, RentBookRequest request) throws BookNotFoundException, BookIsAlreadyRentedOutException, NotAbleToRentException, UserNotFoundException {
        BookStore book = BookRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book is not present"));
        User user = getUserWithRentedBooks(request.getUserId());
        if (!book.getAvailable()) {
            throw new BookIsAlreadyRentedOutException("Book is already rented out");
        }
        if (user.getBooks().size() >= 2) {
            throw new NotAbleToRentException(
                    "Not able to rent book, you already rented 2 books"
            );
        }
        book.setAvailable(false);
        List<BookStore> books = user.getBooks();
        books.add(book);
        book.setUser(user);
        user.setBooks(books);
        userRepository.save(user);
        return RentBookResponse
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .books(user.getBooks())
                .role(user.getRole())
                .build();
    }

    @Override
    public RentBookResponse returnBook(Long id, RentBookRequest request) throws UserNotFoundException, BookNotFoundException {
        User user = getUserWithRentedBooks(request.getUserId());
        BookStore book = BookRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book is not  found"));
        List<BookStore> books = user.getBooks();
        if (!books.contains(book)) {
            throw new BookNotFoundException("Book is not found");
        }
        book.setAvailable(true);
        book.setUser(null);
        books.remove(book);
        user.setBooks(books);
        userRepository.save(user);
        return RentBookResponse
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .books(user.getBooks())
                .role(user.getRole())
                .build();
    }

    private User getUserWithRentedBooks(Long id) throws UserNotFoundException {
        return userRepository
                .findUserWithRentedBooksById(id)
                .orElseThrow(() -> new UserNotFoundException("User is not found"));
    }
}
