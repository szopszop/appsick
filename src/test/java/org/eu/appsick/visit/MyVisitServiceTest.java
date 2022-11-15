package org.eu.appsick.visit;

import org.eu.appsick.clinic.Clinic;
import org.eu.appsick.clinic.ClinicService;
import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.doctor.DoctorService;
import org.eu.appsick.user.patient.Patient;
import org.eu.appsick.user.patient.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
@WebMvcTest(VisitService.class)
@ContextConfiguration(classes = {VisitDao.class})
class MyVisitServiceTest {

    private final VisitDao visitDao = Mockito.mock(VisitDao.class);
    private VisitService visitService;

    @BeforeEach
    void init(){
        visitService = new MyVisitService(visitDao);
    }

    @Test
    void getVisitById() {
        Visit visit = new Visit();
        Mockito.when(visitDao.getVisitById(visit.getVisitId())).thenReturn(Optional.of(visit));
        assertEquals(visitService.getVisitById(visit.getVisitId()), Optional.of(visit));
    }

    @Test
    void getPatientVisits() {
        Patient patient = new Patient();
        List<Visit> visitList = new ArrayList<>();
        for (int i=0; i<10; i++){
            visitList.add(new Visit(0, patient, null, null, null, false, null, null));
        }
        Mockito.when(visitDao.getVisitsByPatient(patient)).thenReturn(visitList);
        assertEquals(visitService.getPatientVisits(patient), visitList);
    }

    @Test
    void getDoctorVisits() {
        Doctor doctor = new Doctor();
        List<Visit> visitList = new ArrayList<>();
        for (int i=0; i<10; i++){
            visitList.add(new Visit(0, null, doctor, null, null, false, null, null));
        }
        Mockito.when(visitDao.getVisitsByDoctor(doctor)).thenReturn(visitList);
        assertEquals(visitService.getDoctorVisits(doctor), visitList);
    }

    @Test
    void getClinicVisits() {
        Clinic clinic = new Clinic();
        List<Visit> visitList = new ArrayList<>();
        for (int i=0; i<10; i++){
            visitList.add(new Visit(0, null, null, clinic, null, false, null, null));
        }
        Mockito.when(visitDao.getVisitsByClinic(clinic)).thenReturn(visitList);
        assertEquals(visitService.getClinicVisits(clinic), visitList);
    }

    @Test
    void addVisit() {
        assertTrue(visitService.addVisit(new Visit()));
    }

    @Test
    @DisplayName("editVisit returns true when visit exists")
    void editExistingVisit() {
        Visit visit = new Visit();
        Mockito.when(visitDao.getVisitById(visit.getVisitId())).thenReturn(Optional.of(visit));
        assertTrue(visitService.editVisit(visit.getVisitId(), visit));
    }
    @Test
    @DisplayName("editVisit returns false when visit doesn't exist")
    void editNonExistingVisit() {
        Visit visit = new Visit();
        Mockito.when(visitDao.getVisitById(visit.getVisitId())).thenReturn(Optional.empty());
        assertFalse(visitService.editVisit(visit.getVisitId(), visit));
    }

    @Test
    @DisplayName("deleteVisit returns true when visit exists")
    void deleteExistingVisit() {
        Visit visitToDelete = new Visit();
        Mockito.when(visitDao.getVisitById(visitToDelete.getVisitId())).thenReturn(Optional.of(visitToDelete));
        assertTrue(visitService.editVisit(visitToDelete.getVisitId(), visitToDelete));
    }
    @Test
    @DisplayName("deleteVisit returns false when visit doesn't exist")
    void deleteNonExistingVisit() {
        Visit visitToDelete = new Visit();
        Mockito.when(visitDao.getVisitById(visitToDelete.getVisitId())).thenReturn(Optional.empty());
        assertFalse(visitService.editVisit(visitToDelete.getVisitId(), visitToDelete));
    }
}