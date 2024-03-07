package Discipline.CineHub.service.chat;

import Discipline.CineHub.dto.chat.ChatMessageResponseDto;
import Discipline.CineHub.repository.UserRepository;
import Discipline.CineHub.domain.ChatMessage;
import Discipline.CineHub.dto.chat.ChatMessageRequestDto;
import Discipline.CineHub.repository.chat.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    @Transactional
    public void save(final ChatMessageRequestDto requestDto) {
        ChatMessage chatMessage = requestDto.toEntity();
        chatMessageRepository.save(chatMessage);
    }

    @Transactional
    public List<ChatMessageResponseDto> findRoom(int roomNumber) {

        List<ChatMessage> chatMessage = this.chatMessageRepository.findByRoomNumber(roomNumber);

        List<ChatMessageResponseDto> dtolist = new ArrayList<>();
        for (ChatMessage chatMessage1 : chatMessage) {
            ChatMessageResponseDto responseDto = new ChatMessageResponseDto(chatMessage1);
            dtolist.add(responseDto);
        }
        return dtolist;
    }
}

