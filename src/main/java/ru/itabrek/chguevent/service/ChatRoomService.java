package ru.itabrek.chguevent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itabrek.chguevent.entity.ChatRoom;
import ru.itabrek.chguevent.repo.ChatRoomRepo;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepo chatRoomRepo;

    public String findChatIdBySenderIdAndRecipientId(Long senderId, Long recipientId, boolean needCreateIfNotExist) {

        if(!chatRoomRepo.existsBySenderIdAndRecipientId(senderId, recipientId) && needCreateIfNotExist){
            String chatId = String.format("%d_%d", senderId, recipientId);

            ChatRoom senderRecipient = ChatRoom
                    .builder()
                    .chatId(chatId)
                    .senderId(senderId)
                    .recipientId(recipientId)
                    .build();

            ChatRoom recipientSender = ChatRoom
                    .builder()
                    .chatId(chatId)
                    .senderId(recipientId)
                    .recipientId(senderId)
                    .build();

            chatRoomRepo.save(senderRecipient);
            chatRoomRepo.save(recipientSender);

            return chatId;

        } else {
            return chatRoomRepo.findBySenderIdAndRecipientId(senderId, recipientId).get().getChatId();
        }
    }
}
