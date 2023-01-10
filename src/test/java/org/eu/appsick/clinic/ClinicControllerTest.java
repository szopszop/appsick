package org.eu.appsick.clinic;

import org.eu.appsick.user.doctor.DoctorService;
import org.eu.appsick.user.patient.PatientService;
import org.eu.appsick.visit.VisitController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VisitController.class)
@ContextConfiguration(classes = {DoctorService.class, PatientService.class, ClinicService.class})
public class ClinicControllerTest {
}
