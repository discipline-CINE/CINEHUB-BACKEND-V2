package com.Discipline.cinehub.dto.chat;

import com.Discipline.cinehub.domain.ChatMessage;
import lombok.Getter;

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
        this.createdTime = chatMessage.getCreatedTime();
    }
}
