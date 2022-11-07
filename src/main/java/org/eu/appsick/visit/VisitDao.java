package org.eu.appsick.visit;

import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;

import java.time.LocalDateTime;
import java.util.List;


public interface VisitDao {

    Visit getVisit(long visitId);
    List<Visit> getVisitList(Patient patient);
    List<Visit> getVisitList(Doctor doctor);
    boolean addVisit(Visit visit);
    boolean deleteVisit(Visit visit);
    boolean editVisit(long visitId,
                      long patientId,
                      long doctorId,
                      long clinicId,
                      LocalDateTime date,
                      boolean online,
                      String reason,
                      Visit.VisitStatus status);

}
