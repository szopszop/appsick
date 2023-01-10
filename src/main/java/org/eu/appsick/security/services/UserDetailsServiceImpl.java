package org.eu.appsick.security.services;

import org.eu.appsick.user.MyUserService;
import org.eu.appsick.user.User;
import org.eu.appsick.user.UserRepository;
import org.eu.appsick.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;




    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        return UserDetailsImpl.build(user);
    }

    public void processOAuthPostLogin(String email) {
        Optional<User> existUser = userRepository.findUserByEmail(email);
        if (!existUser.isPresent()) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setProvider(User.Provider.GOOGLE);
            newUser.setRole(User.Role.PATIENT);
            userRepository.save(newUser);

        }
    }




}
