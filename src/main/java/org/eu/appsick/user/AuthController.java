package org.eu.appsick.user;

import org.eu.appsick.visit.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final MyUserService userService;
    private final UserRepository repository;

    @Autowired
    public AuthController(MyUserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.repository = userRepository;
    }

    @GetMapping("/{userId}")
    public Optional<User> getUserByUserId(@PathVariable Long userId) {
        return this.userService.getUserById(userId);
    }


    @GetMapping(value = "/register/", produces = "application/json")
    @ResponseBody
    public Boolean getUserByEmail(@RequestParam String email) {
        Optional<User> user = repository.findUserByEmail(email);
        return user.isPresent();
    }

    @PostMapping("/register")
    public ResponseEntity<User> postVisit(@RequestBody User user) {
        repository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
