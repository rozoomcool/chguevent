package ru.itabrek.chguevent.repo;

import org.springframework.data.repository.CrudRepository;
import ru.itabrek.chguevent.entity.ChatRoom;

import java.util.Optional;

public interface ChatRoomRepo extends CrudRepository<ChatRoom, Long> {
    boolean existsBySenderIdAndRecipientId(Long senderId, Long recipientId);
    Optional<ChatRoom> findBySenderIdAndRecipientId(Long senderId, Long recipientId);
}
