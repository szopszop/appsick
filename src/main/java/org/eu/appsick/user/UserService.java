package org.eu.appsick.user;

import org.eu.appsick.payload.request.LoginRequest;
import org.eu.appsick.payload.request.RegisterRequest;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    Optional<User> getUserById(Long userId);
    void addUser(User user);
    User addUser(RegisterRequest registerRequest);
    boolean isUserExistsByEmail(RegisterRequest registerRequest);
    ResponseCookie authenticateUser(LoginRequest loginRequest);
    ResponseCookie logoutUser();

}
