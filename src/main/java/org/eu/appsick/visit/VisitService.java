package org.eu.appsick.visit;

import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

public interface VisitService {

    Optional<Visit> getVisitById(long id);
    List<Visit> getPatientVisits(Patient patient);
    List<Visit> getDoctorVisits(Doctor doctor);
    List<Visit> getClinicVisits(Clinic clinic);
    List<Visit> getPastVisitsByPatient(Patient patient);
    List<Visit> getFutureVisitsByPatient(Patient patient);
    List<Visit> getCurrentVisitsByPatient(long patientId);
    boolean addVisit(Visit visit);
    boolean editVisit(long visitID, Visit editedVisit);

    boolean deleteVisit(long visitId);
}

