package org.eu.appsick.examination;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Examination {

    private long examinationId;
    private String examinationName;
    private String summary;
    private boolean completed;
    private LocalDate examinationDate;
}
