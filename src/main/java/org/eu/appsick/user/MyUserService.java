package org.eu.appsick.user;

import org.eu.appsick.payload.request.LoginRequest;
import org.eu.appsick.payload.request.RegisterRequest;
import org.eu.appsick.security.jwt.JwtUtils;
import org.eu.appsick.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserService implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public MyUserService(UserRepository userRepository, PasswordEncoder encoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findUserByUserId(userId);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User addUser(RegisterRequest registerRequest) {
        User user = new User(registerRequest.getBirthDate(), registerRequest.getEmail(), registerRequest.getFirstName(), registerRequest.getLastName(),
                encoder.encode(registerRequest.getPassword()),
                registerRequest.getSex(), registerRequest.getTelephoneNumber(), registerRequest.getRole());
        user.setProvider(User.Provider.LOCAL);
        userRepository.save(user);
        return user;
    }

    @Override
    public boolean isUserExistsByEmail(RegisterRequest registerRequest) {
        return userRepository.existsByEmail(registerRequest.getEmail());
    }

    @Override
    public ResponseCookie authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return jwtUtils.generateJwtCookie(userDetails);
    }

    @Override
    public ResponseCookie logoutUser() {
        return jwtUtils.getCleanJwtCookie();
    }


}
