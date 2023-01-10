package org.eu.appsick.clinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClinicService.class)
@ContextConfiguration(classes = {ClinicRepository.class})
public class ClinicServiceTest {

    @Mock
    private ClinicRepository clinicRepository;
    private ClinicService clinicService;

    @BeforeEach
    void init(){
        clinicService = new MyClinicService(clinicRepository);
    }


}
