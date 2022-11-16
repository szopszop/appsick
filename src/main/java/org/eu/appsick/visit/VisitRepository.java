package org.eu.appsick.visit;

import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    Optional<Visit> findVisitByVisitId(long visitId);

    List<Visit> findVisitsByPatient(Patient patient);
    @Query("SELECT v FROM Visit v WHERE v.date < current_date ")
    List<Visit> findPreviousVisitsByPatient(Patient patient);
    @Query("SELECT v FROM Visit v WHERE v.date > current_date ")
    List<Visit> findFutureVisitsByPatient(Patient patient);
    @Query("SELECT v FROM Visit v WHERE v.date = :now ")
    List<Visit> findCurrentVisitsByPatient(Patient patient, LocalDateTime now);
    List<Visit> findVisitsByDoctor(Doctor doctor);
    List<Visit> findVisitsByClinic(Clinic clinic);

}
