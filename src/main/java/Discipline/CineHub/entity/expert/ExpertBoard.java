package Discipline.CineHub.entity.expert;

import Discipline.CineHub.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.net.URL;
import java.util.ArrayList;
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
  String type;
  String content;
  URL thumbnail;
  // Standard 가격
  int sPrice;
  // Deluxe 가격
  int dPrice;
  // Premium 가격
  int pPrice;
//  @ElementCollection(fetch = FetchType.EAGER) List<URL> imgs;

  @JsonBackReference
  @JoinColumn(name = "user_id")
  @OneToOne
  UserEntity user;

  @JsonManagedReference
  @ToString.Exclude
  @OneToMany(mappedBy="expertBoard", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  List<Reservation> reservations;

  @OneToMany(fetch = FetchType.EAGER)
  List<PriceFeat> priceFeats;

  @OneToMany(mappedBy="expertBoard", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  List<ExpertComment> expertComments;
}
