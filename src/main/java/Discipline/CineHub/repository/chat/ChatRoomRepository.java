package com.Discipline.cinehub.repository.chat;

import com.Discipline.cinehub.domain.ChatRoom;
import com.Discipline.cinehub.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByRoomNumber(int roomNumber);

    Optional<ChatRoom> findByMemberAndOther(UserEntity member, UserEntity other);
}