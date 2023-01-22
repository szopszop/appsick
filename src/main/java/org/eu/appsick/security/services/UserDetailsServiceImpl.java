package org.eu.appsick.security.services;

import org.eu.appsick.user.CustomOAuth2User;
import org.eu.appsick.user.User;
import org.eu.appsick.user.UserRepository;
import org.eu.appsick.user.patient.Patient;
import org.eu.appsick.user.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
        return UserDetailsImpl.build(user);
    }

    public void processOAuthPostLogin(CustomOAuth2User user) {
        Optional<User> existUser = userRepository.findUserByEmail(user.getEmail());
        if (!existUser.isPresent()) {
            User newUser = new User();
            newUser.setEmail(user.getEmail());
            newUser.setImage(user.getPicture());
            newUser.setPassword("no_password_configured_yet");
            newUser.setProvider(User.Provider.GOOGLE);
            newUser.setRole(User.Role.PATIENT);
            newUser.setBirthDate(LocalDate.now());
            newUser.setFirstName(user.getGivenName());
            newUser.setLastName(user.getFamilyName());
            newUser.setSex(User.Sex.MALE);
            newUser.setTelephoneNumber("00 000 00 00");
            Long userId = userRepository.save(newUser).getUserId();
            newUser.setUserId(userId);

            Patient newPatient = new Patient();
            newPatient.setPesel("00000000000");
            newPatient.setPremium(true);
            newPatient.setUser(newUser);
            patientRepository.save(newPatient);
        }
    }




}
