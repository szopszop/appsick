package org.eu.appsick.user;

import java.util.Optional;

public interface UserDao {

    Optional<User> getUserById(long userId);

}
