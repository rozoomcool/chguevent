package ru.itabrek.chguevent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itabrek.chguevent.entity.ChatMessage;
import ru.itabrek.chguevent.entity.ChatNotification;
import ru.itabrek.chguevent.service.ChatMessageService;
import ru.itabrek.chguevent.service.ChatRoomService;

@Controller
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

    @MessageMapping("/application")
    @SendTo("/all/messages")
    public Message send(final Message message) throws Exception {
        return message;
    }

}
