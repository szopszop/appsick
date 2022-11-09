package org.eu.appsick.visit;

import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public List<Visit> getVisitsByPatientId(long patientId) {
        return visitRepository.findVisitsByPatientId(patientId);
    }

    @Override
    public List<Visit> getVisitsByDoctorId(long doctorId) {
        return visitRepository.findVisitsByDoctorId(doctorId);
    }

    @Override
    public List<Visit> getVisitsByClinicId(long clinicId) {
        return visitRepository.findVisitsByClinicId(clinicId);
    }

    @Override
    public void add(Visit visit) {
        visitRepository.save(visit);
    }

    @Override
    public void remove(Visit visit) {
        visitRepository.delete(visit);
    }

    //only for testing TODO: delete
    @Override
    public List<Visit> getAll() {
        return visitRepository.findAll();
    }
}
