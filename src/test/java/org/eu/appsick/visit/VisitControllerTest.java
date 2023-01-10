package org.eu.appsick.visit;

import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.clinic.ClinicService;
import org.eu.appsick.mail.EmailService;
import org.eu.appsick.user.User;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.doctor.DoctorService;
import org.eu.appsick.user.patient.Patient;
import org.eu.appsick.user.patient.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VisitController.class)
@ContextConfiguration(classes = {DoctorService.class, PatientService.class, ClinicService.class})
class VisitControllerTest {
    @Mock
    private VisitService visitService;
    @Mock
    private DoctorService doctorService;
    @Mock
    private PatientService patientService;
    @Mock
    private ClinicService clinicService;
    @Mock
    private EmailService emailService;
    @Mock
    private VisitController visitController;

    @BeforeEach
    void init(){
        visitController = new VisitController(
                visitService,
                doctorService,
                patientService,
                clinicService,
                emailService
        );
    }

    @Test
    void getVisit() {
        Visit newVisit = new Visit();
        Long visitId = newVisit.getVisitId();
        Mockito.when(visitService.getVisitById(visitId)).thenReturn(Optional.of(newVisit));
        assertEquals(visitController.getVisit(visitId), Optional.of(newVisit));
    }

    @Test
    void getDoctorVisits() {
        Doctor doctor = new Doctor();
        List<Visit> visitList = new ArrayList<>();
        for (int i=0; i<10; i++){
            visitList.add(new Visit(2L, null, doctor, null, null, null, false, null, null, Visit.VisitStatus.PENDING, null));
        }
        Mockito.when(doctorService.getDoctorById(doctor.getDoctorId())).thenReturn(Optional.of(doctor));
        Mockito.when(visitService.getDoctorVisits(doctor)).thenReturn(visitList);
        assertEquals(visitController.getDoctorVisits(doctor.getDoctorId()), visitList);
    }

    @Test
    void getPatientVisits() {
        Patient patient = new Patient();
        List<Visit> visitList = new ArrayList<>();
        for (int i=0; i<10; i++){
            visitList.add(new Visit(2L, patient, null, null, null, null, false, null, null, Visit.VisitStatus.PENDING, null));
        }
        Mockito.when(patientService.getPatientById(patient.getPatientId())).thenReturn(Optional.of(patient));
        Mockito.when(visitService.getPatientVisits(patient)).thenReturn(visitList);
        assertEquals(visitController.getPatientVisits(patient.getPatientId()), visitList);
    }

    @Test
    void getClinicVisits() {
        Clinic clinic = new Clinic();
        List<Visit> visitList = new ArrayList<>();
        for (int i=0; i<10; i++){
            visitList.add(new Visit(2L, null, null, clinic, null, null, false, null, null, Visit.VisitStatus.PENDING, null));
        }
        Mockito.when(clinicService.getClinicById(clinic.getClinicId())).thenReturn(Optional.of(clinic));
        Mockito.when(visitService.getClinicVisits(clinic)).thenReturn(visitList);
        assertEquals(visitController.getClinicVisits(clinic.getClinicId()), visitList);
    }

    @Test
    void postVisit() {
        User user = new User();
        user.setEmail("example@email.com");
        Patient patient = new Patient();
        patient.setUser(user);
        Visit visit = new Visit(2L, patient, null, null, null, null,
                false, null, null, Visit.VisitStatus.PENDING, null);
        ResponseEntity<Visit> expectedResponse = new ResponseEntity<>(visit, HttpStatus.CREATED);
        Mockito.when(visitService.addVisit(visit)).thenReturn(true);
        assertEquals(visitController.postVisit(visit), expectedResponse);
    }

    @Test
    @DisplayName("Patch Visit Returns HTTP Status OK when visit exists")
    void patchVisitReturnsHttpStatusOkWhenVisitExists() {
        Visit oldVisit = new Visit(2L, null, null, null, null, null,
                false, null, null, Visit.VisitStatus.PENDING, null);
        Visit newVisit = new Visit(2L, null, null, null, null, null,
                true, null, null, Visit.VisitStatus.PENDING, null);
        Mockito.when(visitService.editVisit(oldVisit.getVisitId(), newVisit)).thenReturn(true);
        ResponseEntity<Visit> expectedResponse = new ResponseEntity<>(newVisit, HttpStatus.OK);
        assertEquals(visitController.patchVisit(oldVisit.getVisitId(), newVisit), expectedResponse);
    }
    @Test
    @DisplayName("Patch Visit Returns HTTP Status NOT FOUND when visit doesn't exist")
    void patchVisitReturnsHttpStatusOkWhenVisitDoesNotExists() {
        Visit oldVisit = new Visit(2L, null, null, null, null, null,
                false, null, null, Visit.VisitStatus.PENDING, null);
        Visit newVisit = new Visit(2L, null, null, null, null, null,
                true, null, null, Visit.VisitStatus.PENDING, null);
        Mockito.when(visitService.editVisit(oldVisit.getVisitId(), newVisit)).thenReturn(false);
        ResponseEntity<Visit> expectedResponse = new ResponseEntity<>(newVisit, HttpStatus.NOT_FOUND);
        assertEquals(visitController.patchVisit(oldVisit.getVisitId(), newVisit), expectedResponse);
    }

    @Test
    void deleteVisit() {
        Visit visitToDelete = new Visit();
        Mockito.when(visitService.deleteVisit(visitToDelete.getVisitId())).thenReturn(true);
        ResponseEntity<Visit> expectedResponse = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        assertEquals(visitController.deleteVisit(visitToDelete.getVisitId()), expectedResponse);
    }
}