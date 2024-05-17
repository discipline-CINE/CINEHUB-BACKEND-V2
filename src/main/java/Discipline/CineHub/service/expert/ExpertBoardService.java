package Discipline.CineHub.service.expert;

import Discipline.CineHub.dto.expert.*;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.entity.expert.ExpertBoard;
import Discipline.CineHub.entity.expert.ExpertComment;
import Discipline.CineHub.entity.expert.PriceFeat;
import Discipline.CineHub.entity.expert.Reservation;
import Discipline.CineHub.repository.expert.ExpertBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpertBoardService {
  @Autowired ExpertBoardRepository expertBoardRepository;
  @Autowired PriceFeatService priceFeatService;

  @Transactional
  public void enrollExpertBoard(ExpertBoardDto expertBoardDto, UserEntity user){
    String title =  expertBoardDto.getTitle();
    int sPrice = expertBoardDto.getSPrice();
    int dPrice = expertBoardDto.getDPrice();
    int pPrice = expertBoardDto.getPPrice();
    String type = expertBoardDto.getType();
    String content = expertBoardDto.getContent();
    URL thumbnail = expertBoardDto.getThumbnail();

    List<PriceFeatDto> priceFeatDtos = expertBoardDto.getPriceFeatDtos();
    List<PriceFeat> priceFeats = new ArrayList<>();

    ExpertBoard expertBoard = new ExpertBoard();
    expertBoard.setTitle(title);
    expertBoard.setSPrice(sPrice);
    expertBoard.setDPrice(dPrice);
    expertBoard.setPPrice(pPrice);
    expertBoard.setType(type);
    expertBoard.setContent(content);
    expertBoard.setThumbnail(thumbnail);
    expertBoard.setUser(user);

    for (PriceFeatDto priceFeatDto : priceFeatDtos){
      PriceFeat pf = priceFeatService.addPriceFeat(priceFeatDto);
      priceFeats.add(pf);
    }
    expertBoard.setPriceFeats(priceFeats);

    ExpertBoard saveExpertBoard = expertBoardRepository.save(expertBoard);

    // ID가 생성된 후 eId 설정
    saveExpertBoard.setEId(saveExpertBoard.getId());

    // 업데이트된 expertBoard 저장
    expertBoardRepository.save(saveExpertBoard);
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

  @Transactional
  public List<LocalDate> checkReservationDate(Long expertBoardId){
    Optional<ExpertBoard> board = expertBoardRepository.findById(expertBoardId);
    List<Reservation> reservations = board.get().getReservations();
    List<LocalDate> reservationDate = new ArrayList<>();

    for(Reservation reservation : reservations){
      LocalDate resDate = reservation.getReservationDate();
      reservationDate.add(resDate);
    }

    return reservationDate;
  }

  public List<GetAllBoardDto> findAllExpertBoards(){
    List<GetAllBoardDto> boardDtos = new ArrayList<>();
    List<ExpertBoard> expertBoards = expertBoardRepository.findAll();

    for (ExpertBoard expertBoard : expertBoards){
      Long id = expertBoard.getId();
      String title = expertBoard.getTitle();
      int sPrice = expertBoard.getSPrice();
      int dPrice = expertBoard.getDPrice();
      int pPrice = expertBoard.getPPrice();
      String type = expertBoard.getType();
      String content = expertBoard.getContent();
      URL thumbnail = expertBoard.getThumbnail();
      List<PriceFeat> priceFeats = expertBoard.getPriceFeats();

      List<PriceFeatDto> priceFeatDtos = new ArrayList<>();

      for(PriceFeat priceFeat : priceFeats){
        PriceFeatDto tmp = new PriceFeatDto(priceFeat.getLabel(), priceFeat.getSFeat(), priceFeat.getDFeat(), priceFeat.getPFeat());
        priceFeatDtos.add(tmp);
      }

      GetAllBoardDto tmp = new GetAllBoardDto(id, title, sPrice, dPrice, pPrice, type, content, thumbnail, priceFeatDtos);
      boardDtos.add(tmp);
    }
    return boardDtos;
  }

  public ExpertBoard getById(Long id){
    Optional<ExpertBoard> expertBoard = expertBoardRepository.findById(id);
    return expertBoard.get();
  }

  public EachBoardDto findById(Long id){
    EachBoardDto boardDto = new EachBoardDto();
    Optional<ExpertBoard> expertBoard = expertBoardRepository.findById(id);

    Long userId = expertBoard.get().getUser().getId();
    String username = expertBoard.get().getUser().getUsername();
    String title = expertBoard.get().getTitle();
    int sPrice = expertBoard.get().getSPrice();
    int dPrice = expertBoard.get().getDPrice();
    int pPrice = expertBoard.get().getPPrice();
    List<ExpertComment> expertComments = expertBoard.get().getExpertComments();
    List<PriceFeat> priceFeats = expertBoard.get().getPriceFeats();
    List<PriceFeatDto> priceFeatDtos = new ArrayList<>();

    List<String> comments = new ArrayList<>();

    for(PriceFeat priceFeat : priceFeats){
      PriceFeatDto priceFeatDto = new PriceFeatDto(priceFeat.getLabel(), priceFeat.getSFeat(), priceFeat.getDFeat(), priceFeat.getPFeat());
      priceFeatDtos.add(priceFeatDto);
    }

    for(ExpertComment expertComment : expertComments){
      String comm = expertComment.getComment();
      comments.add(comm);
    }

    String type = expertBoard.get().getType();
    String content = expertBoard.get().getContent();
    URL thumbnail = expertBoard.get().getThumbnail();
//    List<URL> imgs = expertBoard.get().getImgs();

    boardDto.setId(id);
    boardDto.setUserId(userId);
    boardDto.setUsername(username);
    boardDto.setTitle(title);
    boardDto.setSPrice(sPrice);
    boardDto.setDPrice(dPrice);
    boardDto.setPPrice(pPrice);
    boardDto.setType(type);
    boardDto.setThumbnail(thumbnail);
    boardDto.setContent(content);
    boardDto.setExpertComments(comments);
    boardDto.setPriceFeats(priceFeatDtos);
//    boardDto.setImgs(imgs);

    return boardDto;
  }
}