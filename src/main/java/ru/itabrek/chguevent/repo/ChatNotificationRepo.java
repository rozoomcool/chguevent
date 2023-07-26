package ru.itabrek.chguevent.repo;

import org.springframework.data.repository.CrudRepository;
import ru.itabrek.chguevent.entity.ChatNotification;

public interface ChatNotificationRepo extends CrudRepository<ChatNotification, Long> {
}
