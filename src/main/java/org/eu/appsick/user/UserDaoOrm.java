package org.eu.appsick.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoOrm implements UserDao {

    private final UserRepository userRepository;

    @Autowired
    public UserDaoOrm(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long userId) {
        return userRepository.findUserByUserId(userId);
    }


}
