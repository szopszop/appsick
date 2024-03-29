package org.eu.appsick.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.eu.appsick.medicaldata.MedicalData;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long userId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String image;
    @Enumerated
    private Sex sex;
    private String telephoneNumber;
    private String email;
    private Role role;
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    private Provider provider;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<MedicalData> medicalDataSet;

    public User(LocalDate birthDate, String email, String firstName, String lastName, String password, Sex sex, String telephoneNumber, Role role) {
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public enum Sex {
        MALE, FEMALE
    }

    public enum Provider {
        LOCAL, GOOGLE
    }

    public enum Role {
        PATIENT("ROLE_PATIENT"),
        DOCTOR("ROLE_DOCTOR"),
        ADMIN("ROLE_ADMIN");
        private final String name;

        Role(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
