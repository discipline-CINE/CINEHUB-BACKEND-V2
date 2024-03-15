package Discipline.CineHub.service.chat;

import Discipline.CineHub.domain.ChatMessage;
import Discipline.CineHub.domain.ChatRoom;
import Discipline.CineHub.dto.chat.ChatRoomResponseDto;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.repository.chat.ChatMessageRepository;
import Discipline.CineHub.repository.chat.ChatRoomRepository;
import Discipline.CineHub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;// 채팅방조회
    private final UserRepository userRepository;
    private final ChatMessageRepository chatMessageRepository;

    //전체 채팅방 목록 조회
    @Transactional(readOnly = true)
    public List<ChatRoomResponseDto> findByList() {
        return chatRoomRepository.findAll().stream()
                .map(chatRoom -> {ChatMessage lastMessage = chatMessageRepository.findTopByRoomNumberOrderByCreatedTimeDesc(chatRoom.getRoomNumber());
                    if (lastMessage!=null) {
                        return new ChatRoomResponseDto(chatRoom, lastMessage.getMsg(), lastMessage.getCreatedTime().format(DateTimeFormatter.ISO_DATE_TIME));
                    }
                    else {
                        return new ChatRoomResponseDto(chatRoom, "이 채팅방에 아직 메시지가 없습니다","00:00:00");
                    }
                })
                .collect(Collectors.toList());
    }


    //채팅방 조회
    @Transactional
    public ChatRoom findRoomById(String roomId) {
        int roomNumber = Integer.parseInt(roomId);
        ChatRoom room = chatRoomRepository.findByRoomNumber(roomNumber).orElseThrow(
                () -> new IllegalArgumentException("해당 chatroom이 존재하지 않습니다 +id"+roomId));
        return room;
    }


    //채팅방 개설
    @Transactional
    public ChatRoom createRoom(UserEntity member, UserEntity other) {
        ChatRoom chatRoom=ChatRoom.builder()
                .member(member)
                .other(other)
                .build();
        return this.chatRoomRepository.save(chatRoom);
    }

    //채팅방 삭제
    @Transactional
    public void delete(final int roomNumber) {
        ChatRoom chatRoom = this.chatRoomRepository.findByRoomNumber(roomNumber).orElseThrow(
                ()->new IllegalArgumentException("해당 chatroom이 존재하지 않습니다 +id"+roomNumber));
        //ChatRoom에 해당하는 chatroommessage들도 함께 삭제되도록
        List<ChatMessage> messagesToDelete = chatMessageRepository.findAllByRoomNumber(roomNumber);
        this.chatMessageRepository.deleteAll(messagesToDelete);
        this.chatRoomRepository.delete(chatRoom);
    }

}
