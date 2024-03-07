package Discipline.CineHub.dto.chat;

import java.util.List;

import Discipline.CineHub.domain.ChatRoom;

import Discipline.CineHub.dto.chat.ChatMessageResponseDto;
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