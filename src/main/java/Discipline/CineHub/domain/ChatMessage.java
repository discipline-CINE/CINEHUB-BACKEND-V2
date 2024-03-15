package Discipline.CineHub.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@Table(name = "chat_message")
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String msg;
    private String imageUrl;

    @Column(nullable = false)
    private int roomNumber;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @Builder
    public ChatMessage(String msg, String imageUrl, int roomNumber, String userName) {
        this.msg = msg;
        this.imageUrl = imageUrl;
        this.roomNumber = roomNumber;
        this.userName = userName;
    }
}
