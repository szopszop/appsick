package org.eu.appsick.visit.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eu.appsick.visit.Visit;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class ChatMessage {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long chatId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "visit_id")
    private Visit visit;
    private String author;
    private String message;
    private LocalDateTime date;

    public ChatMessage(Visit visit, String author, String message, LocalDateTime date) {
        this.visit = visit;
        this.author = author;
        this.message = message;
        this.date = date;
    }
}
