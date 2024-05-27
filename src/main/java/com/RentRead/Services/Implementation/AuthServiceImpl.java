package com.RentRead.Services.Implementation;

import com.RentRead.Enum.Role;
import com.RentRead.Exceptions.UserAlreadyExistException;
import com.RentRead.Exceptions.UserNotFoundException;
import com.RentRead.Exchange.Request.LoginRequest;
import com.RentRead.Exchange.Request.UserRegisterRequest;
import com.RentRead.Exchange.Response.RentBookResponse;
import com.RentRead.Models.User;
import com.RentRead.Repository.UserRepository;
import com.RentRead.Services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User registerUser(UserRegisterRequest request) throws UserAlreadyExistException {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistException("User is already register");
        }
        if (request.getRole() == null) {
            request.setRole(Role.USER);
        }
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        return userRepository.save(user);

    }

    @Override
    public String login(LoginRequest request) {
         authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));;
                return "User Logged in successfully!";
    }

    @Override
    public RentBookResponse getUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findUserWithRentedBooksById(id)
                .orElseThrow(() -> new UserNotFoundException("User is not present"));
        return RentBookResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .books(user.getBooks())
                .role(user.getRole())
                .build();
    }
}
