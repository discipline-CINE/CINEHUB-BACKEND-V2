package Discipline.CineHub.service.mypage;

import Discipline.CineHub.dto.expert.*;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.entity.expert.ExpertComment;
import Discipline.CineHub.repository.expert.ExpertCommentRepository;
import Discipline.CineHub.service.expert.ExpertBoardService;
import Discipline.CineHub.service.expert.ReservationService;
import Discipline.CineHub.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MyPageService {
  @Autowired
  ExpertBoardService expertBoardService;

  @Autowired
  UserService userService;

  @Autowired
  ReservationService reservationService;

  @Autowired
  ExpertCommentRepository expertCommentRepository;

  public List<ReservationDto> checkReservation(String username){
    Long id = userService.findByUsername(username).get().getExpertBoard().getId();
    return expertBoardService.checkReservation(id);
  }

  public List<ReservationDtoByUser> checkReservationByUser(String username){
    Optional<UserEntity> user = userService.findByUsername(username);
    return reservationService.findByUser(user.get());
  }

  public List<ExpertBoardIdTitleUsername> checkComplete(String username){
    Optional<UserEntity> user = userService.findByUsername(username);
    return reservationService.checkComplete(user.get());
  }

  public String expertComment(ExpertCommentDto expertCommentDto, Long resId){
    ExpertComment expertComment = new ExpertComment();

    expertComment.setComment(expertCommentDto.getComment());
    expertComment.setExpertBoard(expertCommentDto.getExpertBoard());
    expertComment.setUser(expertCommentDto.getUser());

    reservationService.completeReview(resId);

    return expertCommentDto.getComment();
  }

  @Transactional
  public void deleteCommentById(Long id){
    expertCommentRepository.deleteByIdCustom(id);
  }

}

















