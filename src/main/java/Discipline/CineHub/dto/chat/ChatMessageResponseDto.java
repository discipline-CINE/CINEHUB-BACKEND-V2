package Discipline.CineHub.dto.chat;

import Discipline.CineHub.domain.ChatMessage;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class ChatMessageResponseDto {
    private String userName;
    private String msg;
    private String imageUrl;
    private String createdTime;

    public ChatMessageResponseDto(ChatMessage chatMessage) {
        this.userName = chatMessage.getUserName();
        this.msg = chatMessage.getMsg();
        this.imageUrl = chatMessage.getImageUrl();
        this.createdTime = chatMessage.getCreatedTime().format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
