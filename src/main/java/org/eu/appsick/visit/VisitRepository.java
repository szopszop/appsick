package org.eu.appsick.visit;

import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    Optional<Visit> findVisitByVisitId(Long visitId);

    List<Visit> findVisitsByPatient(Patient patient);

    @Query(value = "SELECT * " +
            "FROM visits v " +
            "JOIN visit_types ON visit_types.visit_visit_id = v.visit_id " +
            "WHERE v.doctor_id = :doctorId AND  EXTRACT(YEAR FROM v.date)  =  :year " +
            "AND EXTRACT(MONTH FROM v.date) =  :month AND " +
            "EXTRACT(DAY FROM v.date )=  :day  " +
            "ORDER BY v.date  ", nativeQuery = true)
    List<Visit> findVisitForDoctorInParticularDay(Long doctorId, int year, int month, int day);


    @Query(value = "SELECT * " +
            "FROM visits v " +
            "JOIN visit_types ON visit_types.visit_visit_id = v.visit_id " +
            "WHERE (v.patient_id = :patientId AND v.date < CAST(now() AS timestamp) + interval '1 hour' AND visit_types.visit_types = :visitType " +
            "AND v.status != 0) " +
            "OR (v.patient_id = :patientId AND v.date = CAST(now() AS timestamp) + interval '1 hour' AND visit_types.visit_types = :visitType " +
            "AND v.status != 0) " +
            "ORDER BY v.date  DESC LIMIT :size " +
            "OFFSET :pageNumber", nativeQuery = true)
    List<Visit> findPastVisitsPagination(Long patientId, Long size, Long pageNumber, Long visitType);

    @Query(value = "SELECT * " +
            "FROM visits v " +
            "WHERE (v.patient_id = :patientId AND v.date < CAST(now() AS timestamp) + interval '1 hour') AND v.status != 0 " +
            "OR (v.patient_id = :patientId AND v.date = CAST(now() AS timestamp) + interval '1 hour' AND v.status != 0) " +
            "ORDER BY v.date DESC LIMIT :size " +
            "OFFSET :pageNumber", nativeQuery = true)
    List<Visit> findPastVisitsPagination(Long patientId, Long size, Long pageNumber);

    @Query(value = "SELECT * " +
            "FROM visits v " +
            "WHERE v.patient_id = :patientId AND v.date > CAST(now() AS timestamp) + interval '1 hour' " +
            "ORDER BY v.date ", nativeQuery = true)
    List<Visit> findFutureVisitsByPatient(Long patientId);

    @Query(value = "SELECT * FROM visits v WHERE v.patient_id = :patientId AND" +
            " extract(year from v.date) = extract(year from now()) AND" +
            " extract(month from v.date) = extract(month from now()) AND" +
            " extract(day from v.date) = extract(day from now()) ORDER BY v.date", nativeQuery = true)
    List<Visit> findCurrentVisitsByPatient(Long patientId);

    List<Visit> findVisitsByDoctor(Doctor doctor);

    List<Visit> findVisitsByClinic(Clinic clinic);

    Long countVisitsByPatient(Patient patient);

    @Query(value = "SELECT * FROM visits v WHERE v.patient_id = :patientId AND v.status = :status", nativeQuery = true)
    List<Visit> findVisitsByPatientAndStatusCompleted(Long patientId, int status);
}
