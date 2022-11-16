package org.eu.appsick.visit;

import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Repository
public class VisitDaoOrm implements VisitDao {

    private final VisitRepository visitRepository;

    @Autowired
    public VisitDaoOrm(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Optional<Visit> getVisitById(long visitId) {
        return visitRepository.findVisitByVisitId(visitId);
    }

    @Override
    public List<Visit> getVisitsByPatient(Patient patient) {
        return visitRepository.findVisitsByPatient(patient);
    }

    @Override
    public List<Visit> getPreviousVisitsByPatient(Patient patient) {
        return visitRepository.findPreviousVisitsByPatient(patient);
    }

    @Override
    public List<Visit> getFutureVisitsByPatient(Patient patient) {
        return visitRepository.findFutureVisitsByPatient(patient);
    }

    @Override
    public List<Visit> getCurrentVisitsByPatient(long patient_id) {
        return visitRepository.getCurrentVisitsByPatient(patient_id);
    }




    @Override
    public List<Visit> getVisitsByDoctor(Doctor doctor) {
        return visitRepository.findVisitsByDoctor(doctor);
    }

    @Override
    public List<Visit> getVisitsByClinic(Clinic clinic) {
        return visitRepository.findVisitsByClinic(clinic);
    }

    @Override
    public void add(Visit visit) {
        visitRepository.save(visit);
    }

    @Override
    public void remove(Visit visit) {
        visitRepository.delete(visit);
    }

}
