package Discipline.CineHub.request.actor;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ActorRequest {
  @NotEmpty
  private String name;
  @NotEmpty
  private String gender;
  @NotEmpty
  private Integer birth;
  @NotEmpty
  private Double height;
  @NotEmpty
  private Double weight;
  @NotEmpty
  private String content;
  private String sns;
  private MultipartFile file;
}
