package com.RentRead.Services.Impl;

import com.RentRead.Exceptions.UserNotFoundException;
import com.RentRead.Models.User;
import com.RentRead.Repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(@Valid User request) {
        User.UserBuilder userBuilder = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail());

        if (request.getRole() != null) {
            userBuilder.role(request.getRole());
        }

        User user = userBuilder.build();

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ConstraintViolationException("Email is already in use", null);
        }

        return userRepository.save(user);
    }

    public User getUserByEmail(@Email String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found for email: " + email));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found for id: " + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
