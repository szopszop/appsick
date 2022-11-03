package org.eu.appsick.visit;

import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.patient.Patient;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
    public boolean editVisit(UUID visitId, Visit editedVisit) {
        Visit oldVisit = null;
        for (Visit visit : visitList) {
            if (visit.getVisitId().equals(editedVisit.getVisitId())) {
                oldVisit = visit;
            }
        }
        if (oldVisit != null) {
            oldVisit.setPatientId(editedVisit.getPatientId());
            oldVisit.setClinicId(editedVisit.getClinicId());
            oldVisit.setDate(editedVisit.getDate());
            oldVisit.setOnline(editedVisit.isOnline());
            oldVisit.setReason(editedVisit.getReason());
            oldVisit.setStatus(editedVisit.getStatus());
        }
        return oldVisit != null;
    }
}
