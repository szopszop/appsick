package org.eu.appsick.visit.chat;

import org.eu.appsick.visit.Visit;
import org.eu.appsick.visit.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chats")
@CrossOrigin(origins = "http://localhost:3000")
public class ChatMessageController {

    private ChatMessageService chatMessageService;
    private VisitService visitService;

    @Autowired
    public ChatMessageController(ChatMessageService chatMessageService, VisitService visitService) {
        this.chatMessageService = chatMessageService;
        this.visitService = visitService;
    }

    @GetMapping("/visit/{visitId}")
    public List<ChatMessage> getChatHistoryFromVisit(@PathVariable long visitId) {
        Optional<Visit> visit = visitService.getVisitById(visitId);
        if (visit.isPresent()) return chatMessageService.getChatHistoryFromVisit(visit.get());
        return new ArrayList<>();
    }

    @PostMapping()
    public ResponseEntity<Void> saveChatHistoryFromVisit(@RequestBody List<ChatMessageDTO> chatMessagesDto) {
        List<ChatMessage> chatMessages = new ArrayList<>();
        for(ChatMessageDTO chatMessageDTO : chatMessagesDto) {
            Optional<Visit> visit = visitService.getVisitById(chatMessageDTO.getVisitId());
            if (visit.isPresent()) {
                chatMessages.add(
                        new ChatMessage(
                                visit.get(),
                                chatMessageDTO.getAuthor(),
                                chatMessageDTO.getMessage(),
                                chatMessageDTO.getDate()
                        )
                );
            }
        }
        chatMessageService.saveChatHistoryFromVisit(chatMessages);
        return ResponseEntity.ok(null);
    }
}