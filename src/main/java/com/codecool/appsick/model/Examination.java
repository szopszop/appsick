package com.codecool.appsick.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Examination {

    private UUID examinationId;
    private String examinationName;
    private String summary;
    private boolean completed;
    private LocalDate examinationDate;
}
