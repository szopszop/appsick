package org.eu.appsick.visit;

import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.clinic.ClinicService;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.doctor.DoctorService;
import org.eu.appsick.user.patient.Patient;
import org.eu.appsick.user.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyVisitService implements VisitService{
    private final VisitDao visitDao;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final ClinicService clinicService;

    @Autowired
    public MyVisitService(VisitDao visitDao, PatientService patientService, DoctorService doctorService, ClinicService clinicService) {
        this.visitDao = visitDao;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.clinicService = clinicService;
    }

    public Optional<Visit> getVisitById(long id) {
        return visitDao.getVisitById(id);
    }

    public List<Visit> getPatientVisits(long patientId) {
        Optional<Patient> patient = patientService.getPatientById(patientId);
        if (patient.isPresent()) return visitDao.getVisitsByPatient(patient.get());
        else return new ArrayList<>();
    }

    public List<Visit> getDoctorVisits(long doctorId) {
        Optional<Doctor> doctor = doctorService.getDoctorById(doctorId);
        if (doctor.isPresent()) return visitDao.getVisitsByDoctor(doctor.get());
        else return new ArrayList<>();
    }

    public List<Visit> getClinicVisits(long clinicId) {
        Optional<Clinic> clinic = clinicService.getClinicById(clinicId);
        if (clinic.isPresent()) return visitDao.getVisitsByClinic(clinic.get());
        else return new ArrayList<>();
    }

    public boolean addVisit(Visit visit) {
        visitDao.add(visit);
        return true;
    }

    public boolean editVisit(long visitId, Visit editedVisit) {
        Optional<Visit> visit = visitDao.getVisitById(visitId);
        if (visit.isPresent()) {
            Visit visitToUpdate = visit.get();
            visitToUpdate.setPatientId(editedVisit.getPatientId());
            visitToUpdate.setDoctorId(editedVisit.getDoctorId());
            visitToUpdate.setClinicId(editedVisit.getClinicId());
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

    //only for testing TODO: delete
    public List<Visit> getAllVisits() {
        return visitDao.getAll();
    }
}
