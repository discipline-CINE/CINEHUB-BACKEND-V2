package Discipline.CineHub.dto.expert;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ExpertBoardRequest {
  String title;
  int sPrice;
  int dPrice;
  int pPrice;
  String type;
  @Size(max = 50000) @Column(nullable = false, length = 50000) String content;
  MultipartFile thumbnailImg;
  List<List<String>> priceFeats;
}
