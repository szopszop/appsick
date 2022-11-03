package org.eu.appsick.visit;

import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;

import java.util.List;
import java.util.UUID;

public interface VisitDao {

    Visit getVisit(UUID visitId);
    List<Visit> getVisitList(Patient patient);
    List<Visit> getVisitList(Doctor doctor);
    void addVisit(Visit visit);
    void deleteVisit(Visit visit);
    void editVisit(UUID uuid, Visit editedVisit);

}
