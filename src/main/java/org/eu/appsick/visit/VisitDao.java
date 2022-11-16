package org.eu.appsick.visit;

import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

public interface VisitDao {

    Optional<Visit> getVisitById(long id);

    List<Visit> getVisitsByPatient(Patient patient);
    List<Visit> getPreviousVisitsByPatient(Patient patient);
    List<Visit> getFutureVisitsByPatient(Patient patient);
//    List<Visit> getCurrentVisitsByPatient(Patient patient, Integer year, Month month, Integer day);

    //    @Override
    //    public List<Visit> getCurrentVisitsByPatient(Patient patient, Integer year, Month month, Integer day) {
    //        return visitRepository.findVisitsByPatientAndDateLessThanAndDateGreaterThan(patient, year, month, day);
    //    }
    List<Visit> getCurrentVisitsByPatient (long patient_id);


    List<Visit> getVisitsByDoctor(Doctor doctor);
    List<Visit> getVisitsByClinic(Clinic clinic);

    void add(Visit visit);
    void remove(Visit visit);

}
