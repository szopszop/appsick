package org.eu.appsick.clinic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Clinic {

    private UUID clinicId;
    private String clinicName;
    private String longitude;
    private String latitude;

}
