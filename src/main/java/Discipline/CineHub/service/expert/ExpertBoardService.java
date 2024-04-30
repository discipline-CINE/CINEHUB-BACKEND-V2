package Discipline.CineHub.service.expert;

import Discipline.CineHub.dto.expert.ExpertBoardDto;
import Discipline.CineHub.dto.expert.ReservationDto;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.entity.expert.ExpertBoard;
import Discipline.CineHub.entity.expert.Reservation;
import Discipline.CineHub.repository.expert.ExpertBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpertBoardService {
  @Autowired ExpertBoardRepository expertBoardRepository;

  public void enrollExpertBoard(ExpertBoardDto expertBoardDto, UserEntity user){
   String title =  expertBoardDto.getTitle();
   int price = expertBoardDto.getPrice();
   String type = expertBoardDto.getType();
   String content = expertBoardDto.getContent();

    ExpertBoard expertBoard = new ExpertBoard();
    expertBoard.setTitle(title);
    expertBoard.setPrice(price);
    expertBoard.setTitle(type);
    expertBoard.setContent(content);
    expertBoard.setUser(user);

    expertBoardRepository.save(expertBoard);
  }

  public List<ReservationDto> checkReservation(Long expertBoardId){
    Optional<ExpertBoard> board = expertBoardRepository.findById(expertBoardId);
    List<Reservation> reservations = board.get().getReservations();
    List<ReservationDto> reservationDtos = new ArrayList<>();

    for(Reservation reservation : reservations){
      ReservationDto reservationDto = new ReservationDto(
              reservation.getName(),
              reservation.getPhone(),
              reservation.getPhone(),
              reservation.getReservationDate()
      );

      reservationDtos.add(reservationDto);
  }
    return reservationDtos;
}
  }