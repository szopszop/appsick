package org.eu.appsick.visit;

import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;

import java.util.List;
import java.util.Optional;

public interface VisitDao {

    Optional<Visit> getVisitById(long id);

    List<Visit> getVisitsByPatient(Patient patient);
    List<Visit> getVisitsByDoctor(Doctor doctor);
    List<Visit> getVisitsByClinic(Clinic clinic);

    void add(Visit visit);
    void remove(Visit visit);

    List<Visit> getAll(); //only for testing TODO: delete
}
