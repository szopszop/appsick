package org.eu.appsick.visit.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {

    private long visitId;
    private String author;
    private String message;
    private LocalDateTime date;
}
