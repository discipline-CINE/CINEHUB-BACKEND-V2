package com.Discipline.cinehub.controller.chat;

import com.Discipline.cinehub.domain.ChatRoom;
import com.Discipline.cinehub.dto.chat.ChatMessageResponseDto;
import com.Discipline.cinehub.dto.chat.ChatRoomRequestDto;
import com.Discipline.cinehub.dto.chat.ChatRoomResponseDto;
import com.Discipline.cinehub.dto.chat.ChatRoomWithMessagesDto;
import com.Discipline.cinehub.entity.UserEntity;
import com.Discipline.cinehub.repository.chat.ChatRoomRepository;
import com.Discipline.cinehub.repository.UserRepository;
import com.Discipline.cinehub.service.chat.ChatMessageService;
import com.Discipline.cinehub.service.chat.ChatRoomService;
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
        UserEntity other = userRepository.findByUsername(chatRoomRequestDto.getNickname())
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


    //전체 조회
    @GetMapping("/list")
    public ResponseEntity<List<ChatRoomResponseDto>> findAll() {
        List<ChatRoomResponseDto> roomResponseDtos = chatRoomService.findByList();
        return ResponseEntity.ok(roomResponseDtos);
    }


    //채팅방 삭제
    @DeleteMapping("/{roomNumber}")
    public ResponseEntity<Void> DeleteChat(@PathVariable int roomNumber) {
        chatRoomService.delete(roomNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
