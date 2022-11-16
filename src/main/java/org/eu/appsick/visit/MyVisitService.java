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
    private final VisitDao visitDao;

    @Autowired
    public MyVisitService(VisitDao visitDao) {
        this.visitDao = visitDao;
    }

    public Optional<Visit> getVisitById(long id) {
        return visitDao.getVisitById(id);
    }

    public List<Visit> getPatientVisits(Patient patient) {
        return visitDao.getVisitsByPatient(patient);
    }

    public List<Visit> getDoctorVisits(Doctor doctor) {
        return visitDao.getVisitsByDoctor(doctor);
    }

    public List<Visit> getClinicVisits(Clinic clinic) {
        return visitDao.getVisitsByClinic(clinic);
    }

    @Override
    public List<Visit> getPastVisitsByPatient(Patient patient) {
        return visitDao.getPreviousVisitsByPatient(patient);
    }

    @Override
    public List<Visit> getFutureVisitsByPatient(Patient patient) {
        return visitDao.getFutureVisitsByPatient(patient);
    }

    @Override
    public List<Visit> getCurrentVisitsByPatient(Patient patient) {
        return visitDao.getCurrentVisitsByPatient(patient);
    }

    public boolean addVisit(Visit visit) {
        visitDao.add(visit);
        return true;
    }

    public boolean editVisit(long visitId, Visit editedVisit) {
        Optional<Visit> visit = visitDao.getVisitById(visitId);
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

    public boolean deleteVisit(long visitId) {
        Optional<Visit> visit = visitDao.getVisitById(visitId);
        if (visit.isPresent()) {
            visitDao.remove(visit.get());
            return true;
        }
        return false;
    }

}
