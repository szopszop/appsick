package org.eu.appsick.clinic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Clinic {

    @Id
    @GeneratedValue
    private long clinicId;
    private String clinicName;
    private String longitude;
    private String latitude;

}
