package org.eu.appsick.user.doctor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DummyDoctorService implements DoctorService {

    @Override
    public Doctor getDoctor() {
        return new Doctor(
                125,
                "Doctor",
                new ArrayList<>(List.of(Doctor.Speciality.MEDICAL_MICROBIOLOGY))
        );
    }
}
