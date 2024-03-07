package com.Discipline.cinehub.dto.chat;

import java.util.List;

import com.Discipline.cinehub.domain.ChatRoom;

import com.Discipline.cinehub.dto.chat.ChatMessageResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomWithMessagesDto {
    private ChatRoom chatRoom;
    private List<ChatMessageResponseDto> messages;
}