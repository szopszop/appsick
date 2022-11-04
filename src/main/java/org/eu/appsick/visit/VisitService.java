package org.eu.appsick.visit;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface VisitService {

    Visit getById(UUID id);
    List<Visit> getPatientVisits(UUID patientId);
    List<Visit> getDoctorVisits(UUID doctorId);
    boolean addVisit(Visit visit);
    boolean editVisit(UUID visitID, Visit editedVisit);
    boolean deleteVisit(UUID visitId);
}
