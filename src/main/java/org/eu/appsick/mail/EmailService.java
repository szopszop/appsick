package org.eu.appsick.mail;

import org.eu.appsick.visit.Visit;

public interface EmailService {

    void sendInfoAboutSuccessfulUserRegistration(String email);
    void sendInfoAboutSuccessfulVisitRegistration(String email, Visit visitDetails);

}
