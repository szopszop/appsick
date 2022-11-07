package org.eu.appsick.clinic;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eu.appsick.visit.Visit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "clinic"
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Visit> visits = new ArrayList<>();

}
