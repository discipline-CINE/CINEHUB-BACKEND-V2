package com.Discipline.cinehub.dto.chat;

import com.Discipline.cinehub.domain.ChatMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageRequestDto {

    public enum MessageType {
        ENTER, TALK
    }

    private String userName; // username
    private String msg; // msg
    private String imageUrl; // file
    private int roomNumber;

    // 생성자
    @Builder
    public ChatMessageRequestDto(String userName, String msg, String imageUrl, int roomNumber) {
        this.msg = msg;
        this.userName = userName;
        this.imageUrl = imageUrl;
        this.roomNumber = roomNumber;
    }

    // 객체 생성
    public ChatMessage toEntity() {
        return ChatMessage.builder()
                .userName(userName)
                .msg(msg)
                .imageUrl(imageUrl)
                .roomNumber(roomNumber)
                .build();
    }
}
