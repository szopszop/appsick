package com.codecool.appsick.user.patient;

import com.codecool.appsick.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper=true)
public class Patient extends User {

    private UUID medicalDataId;
    private String pesel;
    private boolean premium;

    public Patient(UUID userId, String firstName, String lastName, LocalDate birthDate, Sex sex, String telephoneNumber, String email, String password, UUID medicalDataId, String pesel, boolean premium) {
        super(userId, firstName, lastName, birthDate, sex, telephoneNumber, email, password);
        this.medicalDataId = medicalDataId;
        this.pesel = pesel;
        this.premium = premium;
    }
}
