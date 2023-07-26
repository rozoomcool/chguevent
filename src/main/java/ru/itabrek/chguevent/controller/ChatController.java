package ru.itabrek.chguevent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import ru.itabrek.chguevent.entity.ChatMessage;
import ru.itabrek.chguevent.entity.ChatNotification;
import ru.itabrek.chguevent.service.ChatMessageService;
import ru.itabrek.chguevent.service.ChatRoomService;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        String chatId = chatRoomService
                .findChatIdBySenderIdAndRecipientId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId);

        ChatMessage saved = chatMessageService.save(chatMessage);

        messagingTemplate.convertAndSendToUser(
                String.valueOf(chatMessage.getRecipientId()),"/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId(),
                        saved.getSenderName()));
    }
}
