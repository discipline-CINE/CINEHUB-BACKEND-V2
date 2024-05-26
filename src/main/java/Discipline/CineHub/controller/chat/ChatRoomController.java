package Discipline.CineHub.controller.chat;

import Discipline.CineHub.domain.ChatRoom;
import Discipline.CineHub.dto.chat.ChatMessageResponseDto;
import Discipline.CineHub.dto.chat.ChatRoomRequestDto;
import Discipline.CineHub.dto.chat.ChatRoomResponseDto;
import Discipline.CineHub.dto.chat.ChatRoomWithMessagesDto;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.repository.chat.ChatRoomRepository;
import Discipline.CineHub.repository.UserRepository;
import Discipline.CineHub.service.chat.ChatMessageService;
import Discipline.CineHub.service.chat.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatting")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageService chatMessageService;
    private final UserRepository userRepository;

    @PostMapping("/room")
    public ResponseEntity<ChatRoomWithMessagesDto> makeRoom(@RequestBody ChatRoomRequestDto chatRoomRequestDto) {
        String loginMemberId = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity member = userRepository
                .findByUsername(loginMemberId)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
        UserEntity other = userRepository.findByUsername(chatRoomRequestDto.getUsername())
                .orElseThrow(() -> new RuntimeException("채팅 상대방 정보가 없습니다"));
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findByMemberAndOther(member, other);
        //고유한 값이어야 함


        //채팅방이 이미있으면, 반환
        if (optionalChatRoom.isPresent()) {
            ChatRoom foundChatRoom = optionalChatRoom.get();
            List<ChatMessageResponseDto> chatMessage = chatMessageService.findRoom(foundChatRoom.getRoomNumber());
            ChatRoom chatRoom = optionalChatRoom.get();
            ChatRoomWithMessagesDto chatRoomWithMessagesDto = new ChatRoomWithMessagesDto(chatRoom,chatMessage);
            return ResponseEntity.ok(chatRoomWithMessagesDto);
            //없으면 새로 만들기
            //other의 nickname을 받기(채팅 메시지에 user과 other이 있기 떄문에 member는 상관x)
        } else {
            ChatRoom newChatRoom=chatRoomService.createRoom(member, other);
            ChatRoomWithMessagesDto chatRoomWithMessagesDto = new ChatRoomWithMessagesDto(newChatRoom, new ArrayList<>());
            return ResponseEntity.ok(chatRoomWithMessagesDto);
        }
    }

    @GetMapping("/room/{roomNumber}")
    public ResponseEntity<ChatRoomWithMessagesDto> getRoom(@PathVariable int roomNumber) {
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findByRoomNumber(roomNumber);

        if (optionalChatRoom.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ChatRoom foundChatRoom = optionalChatRoom.get();
        List<ChatMessageResponseDto> chatMessage = chatMessageService.findRoom(foundChatRoom.getRoomNumber());
        ChatRoom chatRoom = optionalChatRoom.get();
        ChatRoomWithMessagesDto chatRoomWithMessagesDto = new ChatRoomWithMessagesDto(chatRoom, chatMessage);

        return ResponseEntity.ok(chatRoomWithMessagesDto);
    }


    //전체 조회
    @GetMapping("/list/{username}")
    public ResponseEntity<List<ChatRoomResponseDto>> findByUsername(@PathVariable String username) {
        List<ChatRoomResponseDto> roomResponseDtos = chatRoomService.findByUsername(username);
        return ResponseEntity.ok(roomResponseDtos);
    }


    //채팅방 삭제
    @DeleteMapping("/{roomNumber}")
    public ResponseEntity<Void> DeleteChat(@PathVariable int roomNumber) {
        chatRoomService.delete(roomNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
