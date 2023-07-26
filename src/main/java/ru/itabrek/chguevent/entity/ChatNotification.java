package ru.itabrek.chguevent.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chat_notification")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long senderId;
    private String senderName;
}
