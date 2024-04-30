package Discipline.CineHub.entity.expert;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "reservation")
public class Reservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String name;
  String phone;
  String email;
  LocalDate reservationDate;
  ConfirmType confirm;
  @ManyToOne @JoinColumn(name = "expertboard_id") ExpertBoard expertBoard;
}
