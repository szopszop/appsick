package org.eu.appsick.visit;

import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface VisitDao {

    Visit getVisit(UUID visitId);
    List<Visit> getVisitList(Patient patient);
    List<Visit> getVisitList(Doctor doctor);
    boolean addVisit(Visit visit);
    boolean deleteVisit(Visit visit);
    boolean editVisit(UUID uuid,
                      UUID patientId,
                      UUID doctorId,
                      UUID clinicId,
                      LocalDate date,
                      boolean online,
                      String reason,
                      Visit.VisitStatus status);

}
