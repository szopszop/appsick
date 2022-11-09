package org.eu.appsick.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private long userId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    @Enumerated
    private Sex sex;
    private String telephoneNumber;
    private String email;
    private String password;

    public enum Sex {
        MALE, FEMALE
    }

}
