package ru.itabrek.chguevent.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chat_room")
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String chatId;
    private Long senderId;
    private Long recipientId;
}
