package org.eu.appsick.visit;

import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.doctor.DoctorDao;
import org.eu.appsick.user.patient.Patient;
import org.eu.appsick.user.patient.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class VisitService {
    private final VisitDao visitDao;
    private final PatientDao patientDao;
    private final DoctorDao doctorDao;

    @Autowired
    public VisitService(VisitDao visitDao, PatientDao patientDao, DoctorDao doctorDao){
        this.visitDao = visitDao;
        this.patientDao = patientDao;
        this.doctorDao = doctorDao;
    }

    public Visit getById(String id){
        UUID uuid = UUID.fromString(id);
        return visitDao.getVisit(uuid);
    }

    public List<Visit> getPatientVisits(String patientId){
        UUID uuid = UUID.fromString(patientId);
        Patient patient = patientDao.getById(uuid);
        return visitDao.getVisitList(patient);
    }
    public List<Visit> getDoctorVisits(String doctorId){
        UUID uuid = UUID.fromString(doctorId);
        Doctor doctor = doctorDao.getById(uuid);
        return visitDao.getVisitList(doctor);
    }

    // TODO: pewnie do zmiany, muszę ogarnąć wyciąganie obiektów z JSONa (@JsonProperty ?)
    public boolean addVisit(UUID patientId,
                            UUID doctorId,
                            UUID clinicId,
                            LocalDate date,
                            boolean online,
                            String reason,
                            Visit.VisitStatus status) {
        Visit visit = new Visit(patientId, doctorId, clinicId, date, online, reason, status);
        return visitDao.addVisit(visit);
    }

    public boolean editVisit(UUID visitID,
                             UUID patientId,
                             UUID doctorId,
                             UUID clinicId,
                             LocalDate date,
                             boolean online,
                             String reason,
                             Visit.VisitStatus status){
        return visitDao.editVisit(visitID, patientId, doctorId, clinicId, date, online, reason, status);
    }

    public boolean deleteVisit(UUID visitId){
        return visitDao.deleteVisit(visitDao.getVisit(visitId));
    }

}
