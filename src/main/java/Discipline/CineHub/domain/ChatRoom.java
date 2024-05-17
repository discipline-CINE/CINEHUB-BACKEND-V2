package Discipline.CineHub.domain;

import Discipline.CineHub.entity.BaseTimeEntity;
import Discipline.CineHub.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor
@Getter
@Entity
@Table(name="chatroom")
public class ChatRoom extends BaseTimeEntity { //생성시각 상속
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomNumber;

    @ManyToOne
    @JoinColumn(name="my_id")
    private UserEntity member; //방을 만든 사람

    @ManyToOne
    @JoinColumn(name="other_id")
    private UserEntity other; //대화상대

    @Builder
    public ChatRoom(UserEntity other, UserEntity member) {
        this.other=other;
        this.member=member;
    }


}
