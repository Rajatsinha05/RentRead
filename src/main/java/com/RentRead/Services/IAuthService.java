package com.RentRead.Services;

import com.RentRead.Exceptions.UserAlreadyExistException;
import com.RentRead.Exceptions.UserNotFoundException;
import com.RentRead.Exchange.Request.LoginRequest;
import com.RentRead.Exchange.Request.UserRegisterRequest;
import com.RentRead.Exchange.Response.RentBookResponse;
import com.RentRead.Models.User;

public interface IAuthService {
    public User registerUser(UserRegisterRequest request) throws UserAlreadyExistException;

    public String login(LoginRequest request);

    public RentBookResponse getUserById(Long id) throws UserNotFoundException;
}
