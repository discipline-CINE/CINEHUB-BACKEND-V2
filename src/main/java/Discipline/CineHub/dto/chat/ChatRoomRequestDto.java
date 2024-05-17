// 채팅방 만들 때 요청으로 받는 DTO
// 상대방 닉네임 정보 받아옴

package Discipline.CineHub.dto.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomRequestDto {
    private String username;

    @Builder
    public ChatRoomRequestDto(String username) {

        this.username = username;
    }
}
