package org.eu.appsick.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    Optional<User> getUserById(long userId);

}
