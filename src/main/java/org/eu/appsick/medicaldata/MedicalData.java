package org.eu.appsick.medicaldata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.eu.appsick.user.User;
import org.eu.appsick.visit.Visit;

import javax.persistence.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medical_data")
public class MedicalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long medicalDataId;
    private int weight;
    private int height;
    private String medicalConditions;
    private String allergies;
    private String addictions;
    private String medicaments;
    private String recommendations;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;
}
