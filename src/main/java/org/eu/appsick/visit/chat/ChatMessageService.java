package org.eu.appsick.visit.chat;

import org.eu.appsick.visit.Visit;

import java.util.List;

public interface ChatMessageService {

    List<ChatMessage> getChatHistoryFromVisit(Visit visit);
    void saveChatHistoryFromVisit(List<ChatMessage> chatMessages);
}
