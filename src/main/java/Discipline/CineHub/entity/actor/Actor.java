package Discipline.CineHub.entity.actor;

import Discipline.CineHub.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.net.URL;
import java.util.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(columnList = "name"),
        @Index(columnList = "gender"),
        @Index(columnList = "birth"),
        @Index(columnList = "height"),
        @Index(columnList = "weight"),
        @Index(columnList = "content"),
        @Index(columnList = "sns"),
        @Index(columnList = "thumbnail")
})
@Entity
public class Actor{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;  //id(pk)
  @Setter @Column(nullable = false) private String name;
  @Setter @Column(nullable = false)  private String gender;
  @Setter @Column(nullable = false) private Integer birth;
  @Setter @Column(nullable = false) private Double height;
  @Setter @Column(nullable = false) private Double weight;
  @Setter @Size(max = 5000) @Column(nullable = false, length = 5000) private String content;
  @Setter private String sns;
  @Setter @Column(nullable = true) private URL thumbnailId;
  @Setter private String username;

  @Setter
  @OneToOne(mappedBy = "actor")
  @JsonManagedReference
  private UserEntity user;

  @OrderBy("id")
  @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @ToString.Exclude //circular referencing 이슈 방지
  private final List<ActorComment> actorComments = new ArrayList<>();

  @Builder
  public Actor(String name, String gender, Integer birth, Double height, Double weight, String content, String sns, URL thumbnailId) {
    this.name = name;
    this.gender = gender;
    this.birth = birth;
    this.height = height;
    this.weight = weight;
    this.content = content;
    this.sns = sns;
    this.thumbnailId = thumbnailId;
  }

  //id가 null 일 수도 있다. 따라서 null 인지도 비교해줘야 한다.
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Actor actor)) return false;
    return id != null && id.equals(actor.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
