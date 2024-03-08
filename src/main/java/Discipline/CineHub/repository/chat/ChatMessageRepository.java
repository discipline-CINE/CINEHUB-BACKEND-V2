package Discipline.CineHub.repository.chat;

import Discipline.CineHub.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {
    List<ChatMessage> findByRoomNumber(int roomNumber);

    ChatMessage findTopByRoomNumberOrderByCreatedTimeDesc(int roomNumber);

    List<ChatMessage> findAllByRoomNumber(int roomNumber);
}
