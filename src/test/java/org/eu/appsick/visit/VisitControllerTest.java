package org.eu.appsick.visit;

import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.clinic.ClinicService;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.doctor.DoctorService;
import org.eu.appsick.user.patient.Patient;
import org.eu.appsick.user.patient.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VisitController.class)
@ContextConfiguration(classes = {DoctorService.class, PatientService.class, ClinicService.class})
public class VisitControllerTest {

    private final VisitService visitService = Mockito.mock(VisitService.class);
    private final DoctorService doctorService = Mockito.mock(DoctorService.class);
    private final PatientService patientService = Mockito.mock(PatientService.class);
    private final ClinicService clinicService = Mockito.mock(ClinicService.class);
    private VisitController visitController;

    @BeforeEach
    void init(){
        visitController = new VisitController(
                visitService,
                doctorService,
                patientService,
                clinicService
        );
    }

    @Test
    void getVisit() {
        Visit newVisit = new Visit();
        long visitId = newVisit.getVisitId();
        Mockito.when(visitService.getVisitById(visitId)).thenReturn(Optional.of(newVisit));
        assertEquals(visitController.getVisit(visitId), Optional.of(newVisit));
    }

    @Test
    void getDoctorVisits() {
        Doctor doctor = new Doctor();
        List<Visit> visitList = new ArrayList<>();
        for (int i=0; i<10; i++){
            visitList.add(new Visit(0, null, doctor, null, null, false, null, null));
        }
        Mockito.when(doctorService.getDoctorById(doctor.getDoctorId())).thenReturn(Optional.of(doctor));
        Mockito.when(visitService.getDoctorVisits(doctor)).thenReturn(visitList);
        assertEquals((visitController.getDoctorVisits(doctor.getDoctorId())), visitList);
    }

    @Test
    void getPatientVisits() {
        Patient patient = new Patient();
        List<Visit> visitList = new ArrayList<>();
        for (int i=0; i<10; i++){
            visitList.add(new Visit(0, patient, null, null, null, false, null, null));
        }
        Mockito.when(patientService.getPatientById(patient.getPatientId())).thenReturn(Optional.of(patient));
        Mockito.when(visitService.getPatientVisits(patient)).thenReturn(visitList);
        assertEquals((visitController.getPatientVisits(patient.getPatientId())), visitList);
    }

    @Test
    void getClinicVisits() {
        Clinic clinic = new Clinic();
        List<Visit> visitList = new ArrayList<>();
        for (int i=0; i<10; i++){
            visitList.add(new Visit(0, null, null, clinic, null, false, null, null));
        }
        Mockito.when(clinicService.getClinicById(clinic.getClinicId())).thenReturn(Optional.of(clinic));
        Mockito.when(visitService.getClinicVisits(clinic)).thenReturn(visitList);
        assertEquals((visitController.getClinicVisits(clinic.getClinicId())), visitList);
    }

    @Test
    void postVisit() {
    }

    @Test
    void patchVisit() {
    }

    @Test
    void deleteVisit() {
    }
}