package org.eu.appsick.security;

import org.eu.appsick.security.jwt.JwtUtils;
import org.eu.appsick.user.patient.Patient;

import org.eu.appsick.user.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
public class VisitAccessFilter extends OncePerRequestFilter {

    private final PatientRepository patientRepository;
    private final JwtUtils jwtUtils;

    @Autowired
    public VisitAccessFilter(JwtUtils jwtUtils, PatientRepository patientRepository) {
        this.jwtUtils = jwtUtils;
        this.patientRepository = patientRepository;
    }

    private String extractPatientId(String requestURI) {
        Pattern pattern = Pattern.compile("api/visit/patient/([0-9]+)");
        Matcher matcher = pattern.matcher(requestURI);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }


    private String getUserEmailFromPatientId(String patientId) {
        Optional<Patient> patient = patientRepository.findByPatientId(Long.valueOf(patientId));
        return patient.map(value -> value.getUser()
                              .getEmail())
                      .orElse(null);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String token = jwtUtils.getJwtFromCookies(request);
        if (token != null && jwtUtils.validateJwtToken(token)) {
            String userEmail = String.valueOf(jwtUtils.getUserEmailFromJwtToken(token));
            String patientId = extractPatientId(request.getRequestURI());
            if (patientId != null && userEmail != null && ! userEmail.equals(getUserEmailFromPatientId(patientId))) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}