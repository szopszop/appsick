package org.eu.appsick.user.doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DummyDoctorService implements DoctorService {

    @Override
    public Doctor getDoctor() {
        return new Doctor(
                UUID.randomUUID(),
                "Doctor",
                new ArrayList<>(List.of(Doctor.Speciality.MEDICAL_MICROBIOLOGY))
        );
    }
}
