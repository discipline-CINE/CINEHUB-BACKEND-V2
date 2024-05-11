package Discipline.CineHub.entity.expert;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ExpertImg {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  URL img;
}
