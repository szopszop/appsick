package org.eu.appsick.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long userId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String image;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return userId != null && Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
