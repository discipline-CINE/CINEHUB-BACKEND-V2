package Discipline.CineHub.repository.chat;

import Discipline.CineHub.domain.ChatRoom;
import Discipline.CineHub.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByRoomNumber(int roomNumber);

    Optional<ChatRoom> findByMemberAndOther(UserEntity member, UserEntity other);
}