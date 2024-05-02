package Discipline.CineHub.service.expert;

import Discipline.CineHub.dto.expert.EachBoardDto;
import Discipline.CineHub.dto.expert.ExpertBoardDto;
import Discipline.CineHub.dto.expert.GetAllBoardDto;
import Discipline.CineHub.dto.expert.ReservationDto;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.entity.expert.ExpertBoard;
import Discipline.CineHub.entity.expert.Reservation;
import Discipline.CineHub.repository.expert.ExpertBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
  public List<ReservationDto> checkReservation(Long expertBoardId){
    Optional<ExpertBoard> board = expertBoardRepository.findById(expertBoardId);
    List<Reservation> reservations = board.get().getReservations();
    List<ReservationDto> reservationDtos = new ArrayList<>();

    for(Reservation reservation : reservations){
      ReservationDto reservationDto = new ReservationDto(
              reservation.getId(),
              reservation.getName(),
              reservation.getPhone(),
              reservation.getEmail(),
              reservation.getReservationDate(),
              reservation.getConfirm()
      );

      reservationDtos.add(reservationDto);
    }
    return reservationDtos;
  }

  public List<GetAllBoardDto> findAllExpertBoards(){
    List<GetAllBoardDto> boardDtos = new ArrayList<>();
    List<ExpertBoard> expertBoards = expertBoardRepository.findAll();

    for (ExpertBoard expertBoard : expertBoards){
      Long id = expertBoard.getId();
      String title = expertBoard.getTitle();
      int price = expertBoard.getPrice();
      String type = expertBoard.getType();
      String content = expertBoard.getContent();

      GetAllBoardDto tmp = new GetAllBoardDto(id, title, price, type, content);
      boardDtos.add(tmp);
    }
    return boardDtos;
  }

  public EachBoardDto findById(Long id){
    EachBoardDto boardDto = new EachBoardDto();
    Optional<ExpertBoard> expertBoard = expertBoardRepository.findById(id);

    Long userId = expertBoard.get().getUser().getId();
    String username = expertBoard.get().getUser().getUsername();
    String title = expertBoard.get().getTitle();
    int price = expertBoard.get().getPrice();
    String type = expertBoard.get().getType();
    String content = expertBoard.get().getContent();

    boardDto.setId(id);
    boardDto.setUserId(userId);
    boardDto.setUsername(username);
    boardDto.setTitle(title);
    boardDto.setPrice(price);
    boardDto.setType(type);
    boardDto.setContent(content);

    return boardDto;
  }
}