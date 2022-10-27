package org.eu.appsick.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {

    private UUID userId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Sex sex;
    private String telephoneNumber;
    private String email;
    private String password;

    public enum Sex {
        MALE, FEMALE
    }

}
