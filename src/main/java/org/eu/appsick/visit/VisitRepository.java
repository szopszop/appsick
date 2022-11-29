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


//    @Query("SELECT v FROM Visit v WHERE v.date < current_date ")
//    List<Visit> findPreviousVisitsByPatient(Patient patient, Pageable page);

    @Query(value = "SELECT * FROM visits v WHERE v.patient_id = :patientId", nativeQuery = true)
    List<Visit> findPastVisitsPagination(Long patientId);

    @Query(value = "SELECT v FROM visits v WHERE v.date > current_date", nativeQuery = true)
    List<Visit> findFutureVisitsByPatient(Patient patient);

    //    List<Visit> findVisitsByPatientAndDate_YearAndDate_MonthAndDate_DayOfMonth(Patient patient, Integer date_year, Month date_month, Integer date_dayOfMonth);
    @Query(value = "SELECT * FROM visits WHERE visits.patient_id = :patient_id AND" +
            " extract(year from visits.date) = extract(year from now()) AND" +
            " extract(month from visits.date) = extract(month from now()) AND" +
            " extract(day from visits.date) = extract(day from now())", nativeQuery = true)
    List<Visit> getCurrentVisitsByPatient (Long patient_id);

    List<Visit> findVisitsByDoctor(Doctor doctor);

    List<Visit> findVisitsByClinic(Clinic clinic);

}
