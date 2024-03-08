// 채팅방 조회 response에 사용하는 DTO

package Discipline.CineHub.dto.chat;

import Discipline.CineHub.domain.ChatRoom;
import lombok.Getter;

@Getter
public class ChatRoomResponseDto {
    private int roomNumber;
    private String nickname;
    private String msg;
    private String messageCreatedDate;

    public ChatRoomResponseDto(ChatRoom chatRoom, String msg, String messageCreatedDate) {
        this.roomNumber = chatRoom.getRoomNumber();
        this.nickname = chatRoom.getOther().getUsername();
        this.msg = msg;
        this.messageCreatedDate = messageCreatedDate;
    }
}
