package org.eu.appsick.clinic;

import org.eu.appsick.user.doctor.Doctor;
import org.eu.appsick.user.doctor.DoctorBuilder;
import org.eu.appsick.user.doctor.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClinicController.class)
@ContextConfiguration(classes = {DoctorService.class, ClinicService.class})
public class ClinicControllerTest {

    private ClinicController clinicController;
    @Mock
    private ClinicService clinicService;
    @Mock
    private DoctorService doctorService;
    private final Random random = new Random();

    @BeforeEach
    void init(){
        clinicController = new ClinicController(clinicService, doctorService);
    }

    @Test
    void getClinicById(){
        Clinic clinic = new ClinicBuilder().setClinicId(random.nextLong()).build();
        Mockito.when(clinicService.getClinicById(clinic.getClinicId()))
                .thenReturn(Optional.of(clinic));
        assertEquals(clinicController.getClinicById(clinic.getClinicId()), Optional.of(clinic));
    }
    @Test
    void getAllClinics(){
        List<Clinic> clinics = new ArrayList<>();
        for (int i=0; i<10; i++){
            clinics.add(
                    new ClinicBuilder().setClinicId(random.nextLong()).build()
            );
        }
        Mockito.when(clinicService.getAllClinics()).thenReturn(clinics);
        assertEquals(clinicController.getAllClinics(), clinics);
    }

    @Test
    void getDoctorsByClinic(){
        List<Doctor> doctors = new ArrayList<>();
        for (int i=0; i<10; i++){
            doctors.add(new DoctorBuilder()
                    .setDoctorId(random.nextLong())
                    .setAbout("Test Doctor " + i)
                    .build()
            );
        }
        Clinic clinic = new ClinicBuilder().setClinicId(42L).setAvailableDoctors(doctors).build();
        Mockito.when(doctorService.getDoctorsByClinic(clinic.getClinicId())).thenReturn(doctors);
        assertEquals(clinicController.getDoctorsByClinic(clinic.getClinicId()), doctors);
    }


}
