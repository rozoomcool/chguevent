package ru.itabrek.chguevent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itabrek.chguevent.entity.ChatMessage;
import ru.itabrek.chguevent.repo.ChatMessageRepo;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepo chatMessageRepo;

    public ChatMessage save(ChatMessage chatMessage) {
        chatMessageRepo.save(chatMessage);
        return chatMessage;
    }
}
