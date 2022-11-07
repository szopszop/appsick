package org.eu.appsick.visit;

import java.time.LocalDate;
import java.util.List;


public interface VisitService {

    Visit getById(long id);
    List<Visit> getPatientVisits(long patientId);
    List<Visit> getDoctorVisits(long doctorId);
    boolean addVisit(Visit visit);
    boolean editVisit(long visitID, Visit editedVisit);
    boolean deleteVisit(long visitId);
}
