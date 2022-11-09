package org.eu.appsick.visit;

import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    Optional<Visit> findVisitByVisitId(long visitId);

    List<Visit> findVisitsByPatientId(long patientId);
    List<Visit> findVisitsByDoctorId(long doctorId);
    List<Visit> findVisitsByClinicId(long clinicId);

}
