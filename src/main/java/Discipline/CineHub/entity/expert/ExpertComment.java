package Discipline.CineHub.entity.expert;

import Discipline.CineHub.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "expert_comment")
public class ExpertComment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String comment;
  @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "expert_id") ExpertBoard expertBoard;
  @ManyToOne(fetch = FetchType.EAGER) UserEntity user;
}
