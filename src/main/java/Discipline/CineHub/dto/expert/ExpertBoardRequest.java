package Discipline.CineHub.dto.expert;

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
  String content;
  MultipartFile thumbnailImg;
  List<MultipartFile> imgs;
  List<List<String>> priceFeats;
}
