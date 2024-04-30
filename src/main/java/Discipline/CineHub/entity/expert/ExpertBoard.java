package Discipline.CineHub.entity.expert;

import Discipline.CineHub.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "expert_board")
public class ExpertBoard {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String title;
  int price;
  String type;
  String content;
  @OneToOne @JoinColumn(name = "user_id") UserEntity user;
  @ToString.Exclude @OneToMany(mappedBy="expertBoard", fetch = FetchType.LAZY, cascade = CascadeType.ALL) List<Reservation> reservations;
}
