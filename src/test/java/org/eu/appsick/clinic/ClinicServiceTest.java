package org.eu.appsick.clinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClinicService.class)
@ContextConfiguration(classes = {ClinicRepository.class})
public class ClinicServiceTest {

    @Mock
    private ClinicRepository clinicRepository;
    private ClinicService clinicService;
    private final Random random = new Random();

    @BeforeEach
    void init(){
        clinicService = new MyClinicService(clinicRepository);
    }

    @Test
    void getClinicById(){
        Clinic clinic = new ClinicBuilder().setClinicId(random.nextLong()).build();
        Mockito.when(clinicRepository.findByClinicId(clinic.getClinicId()))
                .thenReturn(Optional.of(clinic));
        assertEquals(clinicService.getClinicById(clinic.getClinicId()), Optional.of(clinic));
    }

    @Test
    void getAllClinics(){
        List<Clinic> clinics = new ArrayList<>();
        for (int i=0; i<10; i++){
            clinics.add(
                    new ClinicBuilder().setClinicName("Test Clinic " + i).build()
            );
        }
        Mockito.when(clinicRepository.findAll()).thenReturn(clinics);
        assertEquals(clinicService.getAllClinics(), clinics);
    }

}
