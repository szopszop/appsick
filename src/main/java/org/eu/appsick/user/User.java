package org.eu.appsick.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;

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

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Patient patient;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Doctor doctor;

    @Enumerated
    private Sex sex;
    private String telephoneNumber;
    private String email;

    @JsonIgnore
    private String password;

    public enum Sex {
        MALE, FEMALE
    }

}
