package org.eu.appsick.visit;

import java.util.List;
import java.util.Optional;


public interface VisitService {

    Optional<Visit> getById(long id);
    List<Visit> getPatientVisits(long patientId);
    List<Visit> getDoctorVisits(long doctorId);
    List<Visit> getClinicVisits(long clinicId);
    boolean addVisit(Visit visit);
    boolean editVisit(long visitID, Visit editedVisit);
    boolean deleteVisit(long visitId);
    List<Visit> getAllVisits();
}
