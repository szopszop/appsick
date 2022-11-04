package org.eu.appsick.visit;

import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class VisitDaoOrm implements VisitDao {
    private final List<Visit> visitList;

    public VisitDaoOrm() {
        this.visitList = new ArrayList<>();
    }

    @Override
    public Visit getVisit(UUID visitId) {
        for (Visit visit : visitList) {
            if (visit.getVisitId().equals(visitId)) {
                return visit;
            }
        }
        return null;
    }

    @Override
    public List<Visit> getVisitList(Patient patient) {
        List<Visit> visits = new ArrayList<>();
        for (Visit visit : visitList) {
            if (visit.getPatientId().equals(patient.getUserId())) {
                visits.add(visit);
            }
        }
        return visits;
    }

    @Override
    public List<Visit> getVisitList(Doctor doctor) {
        List<Visit> visits = new ArrayList<>();
        for (Visit visit : visitList) {
            if (visit.getPatientId().equals(doctor.getUserId())) {
                visits.add(visit);
            }
        }
        return visits;
    }

    @Override
    public boolean addVisit(Visit visit) {
        return visitList.add(visit);
    }

    @Override
    public boolean deleteVisit(Visit visitToDelete) {
        return visitList.remove(visitToDelete);
    }

    @Override
    public boolean editVisit(UUID visitId,
                             UUID patientId,
                             UUID doctorId,
                             UUID clinicId,
                             LocalDateTime date,
                             boolean online,
                             String reason,
                             Visit.VisitStatus status) {
        Visit oldVisit = getVisit(visitId);
        if (oldVisit != null) {
            oldVisit.setPatientId(patientId);
            oldVisit.setClinicId(clinicId);
            oldVisit.setDate(date);
            oldVisit.setOnline(online);
            oldVisit.setReason(reason);
            oldVisit.setStatus(status);
        }
        return oldVisit != null;
    }
}
