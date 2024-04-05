package Discipline.CineHub.entity.actor;

import jakarta.persistence.*;
import lombok.*;

import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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
  @Setter @Column(nullable = false) @Enumerated(EnumType.STRING) private GenderType gender;
  @Setter @Column(nullable = false) private Integer birth;
  @Setter @Column(nullable = false) private Double height;
  @Setter @Column(nullable = false) private Double weight;
  @Setter private String content;
  @Setter private String sns;
  @Setter @Column(nullable = true) private URL ThumbnailId;


  @OrderBy("id")
  @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @ToString.Exclude //circular referencing 이슈 방지
  private final Set<ActorComment> actorComments = new LinkedHashSet<>();

  @Builder
  public Actor(String name, GenderType gender, Integer birth, Double height, Double weight, String content, String sns, URL thumbnailId) {
    this.name = name;
    this.gender = gender;
    this.birth = birth;
    this.height = height;
    this.weight = weight;
    this.content = content;
    this.sns = sns;
    ThumbnailId = thumbnailId;
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
