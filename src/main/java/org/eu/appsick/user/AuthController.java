package org.eu.appsick.user;

import org.eu.appsick.payload.request.LoginRequest;
import org.eu.appsick.payload.request.RegisterRequest;
import org.eu.appsick.payload.response.MessageResponse;
import org.eu.appsick.user.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class AuthController {

    private final UserService userService;
    private final PatientService patientService;

    @Autowired
    public AuthController(UserService userService, PatientService patientService) {
        this.userService = userService;
        this.patientService = patientService;
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerPatient(@Valid @RequestBody RegisterRequest registerRequest) {
        return patientService.addNewPatient(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseCookie> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        ResponseCookie jwtCookie = userService.authenticateUser(loginRequest);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(jwtCookie);
    }

    @GetMapping("/current")
    public Object currentUser(@CurrentSecurityContext(expression = "authentication")
                              Authentication authentication) {
        Object principal = authentication.getPrincipal();
        return principal;
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        ResponseCookie cookie = userService.logoutUser();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("You've been signed out!");
    }

}
