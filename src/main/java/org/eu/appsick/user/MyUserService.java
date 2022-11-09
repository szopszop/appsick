package org.eu.appsick.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserService implements UserService {

    private final UserDao userDao;

    @Autowired
    public MyUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> getUserById(long userId) {
        return userDao.getUserById(userId);
    }
}
