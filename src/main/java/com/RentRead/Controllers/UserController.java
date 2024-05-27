package com.RentRead.Controllers;

import com.RentRead.Exceptions.UserAlreadyExistException;
import com.RentRead.Exceptions.UserNotFoundException;
import com.RentRead.Exchange.Request.LoginRequest;
import com.RentRead.Exchange.Request.UserRegisterRequest;
import com.RentRead.Models.User;
import com.RentRead.Services.IAuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final IAuthService authService;

    @Autowired
    public UserController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) throws UserAlreadyExistException {
        log.info("Registering user: {}", userRegisterRequest);
        User registeredUser = authService.registerUser(userRegisterRequest);
        log.info("User registered successfully: {}", registeredUser);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws UserNotFoundException {
        log.info("Logging in user: {}", loginRequest.getEmail());
        String token = authService.login(loginRequest);
        log.info("User logged in successfully: {}", loginRequest.getEmail());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
