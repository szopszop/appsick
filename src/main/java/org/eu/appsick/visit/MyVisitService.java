package org.eu.appsick.visit;

import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyVisitService implements VisitService{

    private final VisitRepository visitRepository;

    @Autowired
    public MyVisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public Optional<Visit> getVisitById(Long id) {
        return visitRepository.findVisitByVisitId(id);
    }

    public List<Visit> getPatientVisits(Patient patient) {
        return visitRepository.findVisitsByPatient(patient);
    }

    public List<Visit> getDoctorVisits(Doctor doctor) {
        return visitRepository.findVisitsByDoctor(doctor);
    }

    public List<Visit> getClinicVisits(Clinic clinic) {
        return visitRepository.findVisitsByClinic(clinic);
    }

    @Override
    public List<Visit> getPastVisitsByPatient(Patient patient) {
        return visitRepository.findPreviousVisitsByPatient(patient);
    }

    @Override
    public List<Visit> getFutureVisitsByPatient(Patient patient) {
        return visitRepository.findFutureVisitsByPatient(patient);
    }

    @Override
    public List<Visit> getCurrentVisitsByPatient(Long patient_id) {
        return visitRepository.getCurrentVisitsByPatient(patient_id);
    }



    public boolean addVisit(Visit visit) {
        visitRepository.save(visit);
        return true;
    }

    public boolean editVisit(Long visitId, Visit editedVisit) {
        Optional<Visit> visit = visitRepository.findVisitByVisitId(visitId);
        if (visit.isPresent()) {
            Visit visitToUpdate = visit.get();
            visitToUpdate.setPatient(editedVisit.getPatient());
            visitToUpdate.setDoctor(editedVisit.getDoctor());
            visitToUpdate.setClinic(editedVisit.getClinic());
            visitToUpdate.setDate(editedVisit.getDate());
            visitToUpdate.setOnline(editedVisit.isOnline());
            visitToUpdate.setReason(editedVisit.getReason());
            visitToUpdate.setStatus(editedVisit.getStatus());
            return true;
        }
        return false;
    }

    public boolean deleteVisit(Long visitId) {
        Optional<Visit> visit = visitRepository.findVisitByVisitId(visitId);
        if (visit.isPresent()) {
            visitRepository.delete(visit.get());
            return true;
        }
        return false;
    }

}
