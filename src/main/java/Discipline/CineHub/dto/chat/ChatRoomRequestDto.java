// 채팅방 만들 때 요청으로 받는 DTO
// 상대방 닉네임 정보 받아옴

package com.Discipline.cinehub.dto.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomRequestDto {
    private String nickname;

    @Builder
    public ChatRoomRequestDto(String nickname) {

        this.nickname = nickname;
    }
}
