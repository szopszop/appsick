package org.eu.appsick.visit;

import net.minidev.json.JSONArray;
import org.eu.appsick.utils.VisitDate;
import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<Visit> findPastVisitsPagination(Long patientId, Long pageNumber, Set<Long> visitType) {
        long size = 5L;
        Set<Visit> groupedVisits = new HashSet<>();

        if (pageNumber == null) pageNumber = 0L;
        else pageNumber = (pageNumber - 1) * size;
        if (visitType == null) return visitRepository.findPastVisitsPagination(patientId, size, pageNumber);
        else {
            for (Long type: visitType) {
                groupedVisits.addAll(visitRepository.findPastVisitsPagination(patientId, size, pageNumber, type));
            }
            return List.copyOf(groupedVisits);
        }
    }

    public JSONArray getDoctorVisitsInParticularDay(Doctor doctor, VisitDate date) {
        List<Visit> visitForDoctorInParticularDay = visitRepository.findVisitForDoctorInParticularDay(
                doctor.getDoctorId(), date.getYear(), date.getMonth(), date.getDay());
        VisitArray visits = new VisitArray(visitForDoctorInParticularDay);


        return visits.getAvailableSlots();
    }

    @Override
    public List<Visit> findFutureVisitsByPatient(Long patientId) {
        return visitRepository.findFutureVisitsByPatient(patientId);
    }

    @Override
    public List<Visit> findCurrentVisitsByPatient(Long patientId) {
        return visitRepository.findCurrentVisitsByPatient(patientId);
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

    @Override
    public Long countVisitsByPatient(Patient patient) {
        return visitRepository.countVisitsByPatient(patient);
    }

    @Override
    public boolean editStatusVisit(Long visitId, String statusVisit) {

        Optional<Visit> visit = visitRepository.findVisitByVisitId(visitId);
        if (visit.isPresent()) {
            Visit visitToUpdate = visit.get();
            visitToUpdate.setStatus(Visit.VisitStatus.fromValue(statusVisit));
            visitRepository.save(visitToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public List<Visit> findVisitsByPatientAndStatusCompleted(Long patientId, int status) {
        return visitRepository.findVisitsByPatientAndStatusCompleted(patientId, status);
    }

}
