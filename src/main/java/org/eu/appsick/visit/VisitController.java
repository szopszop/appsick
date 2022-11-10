package org.eu.appsick.visit;

import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.clinic.ClinicService;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.doctor.DoctorService;
import org.eu.appsick.user.patient.Patient;
import org.eu.appsick.user.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/visit")
public class VisitController {

    private final VisitService visitService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final ClinicService clinicService;

    @Autowired
    public VisitController(VisitService visitService, DoctorService doctorService, PatientService patientService, ClinicService clinicService) {
        this.visitService = visitService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.clinicService = clinicService;
    }

    @GetMapping(value = "/{visitId}", produces = "application/json")
    public VisitDto getVisit(@PathVariable long visitId) {
        Optional<Visit> visitById = visitService.getVisitById(visitId);
        if (visitById.isPresent()) {
            Visit visit = visitById.get();
            return new VisitDto(
                    visit.getDate(),
                    visit.getReason(),
                    visit.getDoctor().getUser().getFirstName(),
                    visit.getDoctor().getUser().getLastName(),
                    visit.getDoctor().getMedicalSpecialities(),
                    visit.getClinic().getClinicName(),
                    visit.getClinic().getLongitude(),
                    visit.getClinic().getLatitude()
            );
        }
        return null;
    }

    @GetMapping(value = "/doctor/{doctorId}")
    public List<VisitDto> getDoctorVisits(@PathVariable long doctorId) {
        Optional<Doctor> doctor = doctorService.getDoctorById(doctorId);
        if (doctor.isPresent()) {
            List<Visit> doctorVisits = visitService.getDoctorVisits(doctor.get());
            List<VisitDto> doctorVisitsDto = new ArrayList<>();
            for (Visit visit : doctorVisits) {
                doctorVisitsDto.add(
                        new VisitDto(
                                visit.getDate(),
                                visit.getReason(),
                                visit.getDoctor().getUser().getFirstName(),
                                visit.getDoctor().getUser().getLastName(),
                                visit.getDoctor().getMedicalSpecialities(),
                                visit.getClinic().getClinicName(),
                                visit.getClinic().getLongitude(),
                                visit.getClinic().getLatitude()
                        )
                );
            }
            return doctorVisitsDto;
        }
        else return new ArrayList<>();
    }

    @GetMapping(value = "/patient/{patientId}")
    public List<VisitDto> getVisits(@PathVariable long patientId) {
        Optional<Patient> patient = patientService.getPatientById(patientId);
        if (patient.isPresent()) {
            List<Visit> patientVisits = visitService.getPatientVisits(patient.get());
            List<VisitDto> patientVisitsDto = new ArrayList<>();
            for (Visit visit : patientVisits) {
                patientVisitsDto.add(
                        new VisitDto(
                                visit.getDate(),
                                visit.getReason(),
                                visit.getDoctor().getUser().getFirstName(),
                                visit.getDoctor().getUser().getLastName(),
                                visit.getDoctor().getMedicalSpecialities(),
                                visit.getClinic().getClinicName(),
                                visit.getClinic().getLongitude(),
                                visit.getClinic().getLatitude()
                        )
                );
            }
            return patientVisitsDto;
        }
        else return new ArrayList<>();
    }

    @GetMapping(value = "/clinic/{clinicId}")
    public List<VisitDto> getClinicVisits(@PathVariable long clinicId) {
        Optional<Clinic> clinic = clinicService.getClinicById(clinicId);
        if (clinic.isPresent()) {
            List<Visit> clinicVisits = visitService.getClinicVisits(clinic.get());
            List<VisitDto> clinicVisitsDto = new ArrayList<>();
            for (Visit visit : clinicVisits) {
                clinicVisitsDto.add(
                        new VisitDto(
                                visit.getDate(),
                                visit.getReason(),
                                visit.getDoctor().getUser().getFirstName(),
                                visit.getDoctor().getUser().getLastName(),
                                visit.getDoctor().getMedicalSpecialities(),
                                visit.getClinic().getClinicName(),
                                visit.getClinic().getLongitude(),
                                visit.getClinic().getLatitude()
                        )
                );
            }
            return clinicVisitsDto;
        }
        else return new ArrayList<>();
    }

    @PostMapping()
    public ResponseEntity<Visit> postVisit(@RequestBody Visit newVisit) {
        visitService.addVisit(newVisit);
        return new ResponseEntity<>(newVisit, HttpStatus.OK);
    }

    @PatchMapping(value = "/{visitId}")
    public ResponseEntity<Visit> patchVisit(@PathVariable long visitId, @RequestBody Visit editedVisit) {
        return (visitService.editVisit(visitId, editedVisit)) ?
                new ResponseEntity<>(editedVisit, HttpStatus.OK) :
                new ResponseEntity<>(editedVisit, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{visitId}")
    public ResponseEntity<String> deleteVisit(@PathVariable long visitId) {
        return (visitService.deleteVisit(visitId)) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
