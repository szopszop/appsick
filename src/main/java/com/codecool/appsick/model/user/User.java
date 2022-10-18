package com.codecool.appsick.model.user;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class User {

    private UUID userId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Sex sex;
    private String telephoneNumber;
    private String email;
    private String password;

    public enum Sex {
        MALE, FEMALE;
    }

    public User(String email, String password) {
        this.userId = UUID.randomUUID();
        this.email = email;
        this.password = password;
    }

}
