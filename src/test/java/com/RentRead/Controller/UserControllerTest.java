package com.RentRead.Controller;


import com.RentRead.Controllers.UserController;
import com.RentRead.Exceptions.UserAlreadyExistException;
import com.RentRead.Exceptions.UserNotFoundException;
import com.RentRead.Exchange.Request.LoginRequest;
import com.RentRead.Exchange.Request.UserRegisterRequest;
import com.RentRead.Models.User;
import com.RentRead.Services.IAuthService;
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

class UserControllerTest {

    @Mock
    private IAuthService authService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_success() throws UserAlreadyExistException {
        UserRegisterRequest request = new UserRegisterRequest();
        User user = new User();

        when(authService.registerUser(any(UserRegisterRequest.class))).thenReturn(user);

        ResponseEntity<User> response = userController.registerUser(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(authService, times(1)).registerUser(request);
    }


    @Test
    void login_success() throws UserNotFoundException {
        LoginRequest request = new LoginRequest();
        String token = "token";

        when(authService.login(any(LoginRequest.class))).thenReturn(token);

        ResponseEntity<String> response = userController.login(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, response.getBody());
        verify(authService, times(1)).login(request);
    }


}
