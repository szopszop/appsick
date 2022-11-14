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
@WebMvcTest(VisitController.class)
@ContextConfiguration(classes = {DoctorService.class, PatientService.class, ClinicService.class})
public class VisitControllerTest {

    private final VisitService visitService = Mockito.mock(VisitService.class);
    private final DoctorService doctorService = Mockito.mock(DoctorService.class);
    private final PatientService patientService = Mockito.mock(PatientService.class);
    private final ClinicService clinicService = Mockito.mock(ClinicService.class);
    private VisitController visitController;

    @BeforeEach
    void beforeEach(){
        visitController = new VisitController(
                visitService,
                doctorService,
                patientService,
                clinicService
        );
    }

    @Test
    void getBackJustAddedVisit() {

    }

    @Test
    void getDoctorVisits() {
    }

    @Test
    void getVisits() {
    }

    @Test
    void getClinicVisits() {
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