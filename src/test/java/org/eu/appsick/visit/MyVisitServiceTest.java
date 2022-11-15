package org.eu.appsick.visit;

import org.eu.appsick.clinic.ClinicService;
import org.eu.appsick.user.doctor.DoctorService;
import org.eu.appsick.user.patient.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    }

    @Test
    void getPatientVisits() {
    }

    @Test
    void getDoctorVisits() {
    }

    @Test
    void getClinicVisits() {
    }

    @Test
    void addVisit() {
    }

    @Test
    void editVisit() {
    }

    @Test
    void deleteVisit() {
    }
}