package org.eu.appsick.clinic;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Clinic {

    private long clinicId;
    private String clinicName;
    private String longitude;
    private String latitude;

}
