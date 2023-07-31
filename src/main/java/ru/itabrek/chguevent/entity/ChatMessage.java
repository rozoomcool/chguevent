package ru.itabrek.chguevent.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "chat_messages")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String chatId;
    private Long senderId;
    private Long recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    private Date timestamp;
    private MessageStatus status;
}

