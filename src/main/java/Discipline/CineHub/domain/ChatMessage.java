package Discipline.CineHub.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String userName;
    private String msg;
    private String imageUrl;
    private int roomNumber;
    private String createdTime;

    @Builder
    public ChatMessage(String msg, String imageUrl, int roomNumber, String userName) {
        this.msg = msg;
        this.imageUrl = imageUrl;
        this.roomNumber = roomNumber;
        this.userName = userName;
        this.createdTime = ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }
}
