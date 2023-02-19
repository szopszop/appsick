package org.eu.appsick.visit;

import org.eu.appsick.utils.VisitDate;
import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface VisitService {

    Optional<Visit> getVisitById(Long id);
    List<Visit> getPatientVisits(Patient patient);
    List<Visit> getDoctorVisits(Doctor doctor);
    List<Visit> getClinicVisits(Clinic clinic);
    List<Visit> findPastVisitsPagination(Long patientId, Long pageNumber, Set<Long> visitType);
    List<Visit> getDoctorVisitsInParticularDay(Doctor doc, VisitDate day);
    List<Visit> findCurrentVisitsByPatient(Long patientId);
    List<Visit> findFutureVisitsByPatient(Long patientId);

    boolean addVisit(Visit visit);
    boolean editVisit(Long visitID, Visit editedVisit);
    boolean deleteVisit(Long visitId);

    Long countVisitsByPatient(Patient patient);
    boolean editStatusVisit(Long visitId, String statusVisit);

    List<Visit> findVisitsByPatientAndStatusCompleted(Long patientId, int status);
}

