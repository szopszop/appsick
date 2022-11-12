package org.eu.appsick.visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eu.appsick.user.doctor.Doctor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitDto {

    private long visitId;
    private LocalDateTime date;
    private String reason;
    private String doctorFirstName;
    private String doctorLastName;
    private List<Doctor.Speciality> doctorSpecialities;
    private String clinicName;
    private String longitude;
    private String latitude;
}
