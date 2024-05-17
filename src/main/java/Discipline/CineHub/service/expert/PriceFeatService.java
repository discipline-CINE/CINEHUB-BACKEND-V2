package Discipline.CineHub.service.expert;

import Discipline.CineHub.dto.expert.PriceFeatDto;
import Discipline.CineHub.entity.expert.PriceFeat;
import Discipline.CineHub.repository.expert.PriceFeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceFeatService {
  @Autowired
  private PriceFeatRepository priceFeatRepository;

  public PriceFeat addPriceFeat(PriceFeatDto priceFeatDto){
    PriceFeat priceFeat = new PriceFeat();

    priceFeat.setLabel(priceFeatDto.getLabel());
    priceFeat.setSFeat(priceFeatDto.getV1());
    priceFeat.setDFeat(priceFeatDto.getV2());
    priceFeat.setPFeat(priceFeatDto.getV3());

    priceFeatRepository.save(priceFeat);
    return priceFeat;
  }
}
