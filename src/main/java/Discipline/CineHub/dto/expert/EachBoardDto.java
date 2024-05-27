package Discipline.CineHub.dto.expert;

import Discipline.CineHub.entity.expert.ExpertComment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EachBoardDto extends GetAllBoardDto{
  String username;
  Long userId;
  List<CommentWithId> expertComments;
  List<URL> imgs;
}
