package org.eu.appsick.user;

import org.eu.appsick.payload.request.LoginRequest;
import org.eu.appsick.payload.request.RegisterRequest;
import org.eu.appsick.payload.response.MessageResponse;
import org.eu.appsick.payload.response.UserInfoResponse;
import org.eu.appsick.security.jwt.JwtUtils;
import org.eu.appsick.security.services.UserDetailsImpl;
import org.eu.appsick.user.patient.Patient;
import org.eu.appsick.user.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

//    private final MyUserService userService;


    private final UserRepository userRepository;

    private final PatientRepository patientRepository;

    @Autowired
    public AuthController(UserRepository userRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        User user = new User(registerRequest.getBirthDate(), registerRequest.getEmail(), registerRequest.getFirstName(), registerRequest.getLastName(),
                encoder.encode(registerRequest.getPassword()),
                registerRequest.getSex(), registerRequest.getTelephoneNumber(), registerRequest.getRole());


        userRepository.save(user);
        Optional<User> userByEmail = userRepository.findUserByEmail(registerRequest.getEmail());
        Patient patient = new Patient(registerRequest.getPesel(), false, userByEmail.get());

        patientRepository.save(patient);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }

    @PostMapping("/login")
    public ResponseEntity<ResponseCookie> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {


        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        System.out.println(authentication.getPrincipal());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        System.out.println(jwtCookie);

//        return new ResponseEntity<>(jwtCookie, HttpStatus.OK);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(jwtCookie);

    }
    @GetMapping("/current")
    public Object currentUser(@CurrentSecurityContext(expression = "authentication")
  Authentication authentication)
                     {

                         Object principal = authentication.getPrincipal();

                         return principal;
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("You've been signed out!");
    }



//    @Autowired
//    public AuthController(MyUserService userService, UserRepository userRepository) {
//        this.userService = userService;
//        this.repository = userRepository;
//    }
//
//    @GetMapping("/{userId}")
//    public Optional<User> getUserByUserId(@PathVariable Long userId) {
//        return this.userService.getUserById(userId);
//    }
//
//
//    @GetMapping(value = "/register/", produces = "application/json")
//    @ResponseBody
//    public Boolean getUserByEmail(@RequestParam String email) {
//        Optional<User> user = repository.findUserByEmail(email);
//        return user.isPresent();
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<User> postVisit(@RequestBody User user) {
//        repository.save(user);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
}
