package org.eu.appsick.visit.chat;

import org.eu.appsick.visit.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyChatMessageService implements ChatMessageService {

    private ChatMessageRepository chatMessageRepository;

    @Autowired
    public MyChatMessageService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @Override
    public List<ChatMessage> getChatHistoryFromVisit(Visit visit) {
        return chatMessageRepository.findAllByVisit(visit);
    }

    @Override
    public void saveChatHistoryFromVisit(List<ChatMessage> chatMessages) {
        chatMessageRepository.saveAll(chatMessages);
    }
}
