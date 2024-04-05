package Discipline.CineHub.entity.actor;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
})
@Entity
public class ActorComment{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  @ManyToOne(optional = false) private Actor actor;
  @Setter @Column(nullable = false, length = 500) private String content;

  protected ActorComment() {}

  private ActorComment(Actor actor, String content) { //factory 메서드 구현
    this.actor = actor;
    this.content = content;
  }

  public static ActorComment of(Actor actor, String content){
    return new ActorComment(actor, content);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ActorComment that)) return false;
    return id != null && id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
