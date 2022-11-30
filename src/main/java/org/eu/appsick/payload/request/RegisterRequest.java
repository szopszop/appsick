package org.eu.appsick.payload.request;

import lombok.Data;
import org.eu.appsick.user.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class RegisterRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 20)
    private String lastName;


    private LocalDate birthDate;


    private User.Sex sex;


    private User.Role role = User.Role.ROLE_PATIENT;

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
