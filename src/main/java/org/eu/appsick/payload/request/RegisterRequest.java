package org.eu.appsick.payload.request;

import org.eu.appsick.user.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;


public class RegisterRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 20)
    private String lastName;

    @NotBlank
    private LocalDate birthDate;

    @NotBlank
    private User.Sex sex;

    @NotBlank
    private User.Role role;

    @NotBlank
    private String telephoneNumber;


    private boolean premium = false;

    @NotBlank
    private String pesel;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;



}
