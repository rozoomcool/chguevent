package ru.itabrek.chguevent.repo;

import org.springframework.data.repository.CrudRepository;
import ru.itabrek.chguevent.entity.ChatMessage;

public interface ChatMessageRepo extends CrudRepository<ChatMessage, Long> {
}
