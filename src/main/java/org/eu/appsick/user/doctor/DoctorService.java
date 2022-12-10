package org.eu.appsick.user.doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {

    Optional<Doctor> getDoctorById(Long doctorId);
    List<Doctor> getDoctorsByClinic(Long clinicId);
}
