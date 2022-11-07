package org.eu.appsick.user.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    private long patientId;
    private String pesel;
    private boolean premium;

}
