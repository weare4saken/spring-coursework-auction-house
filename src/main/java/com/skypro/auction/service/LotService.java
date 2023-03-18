package com.skypro.auction.service;

import com.skypro.auction.dto.*;
import com.skypro.auction.enums.LotStatus;
import com.skypro.auction.mapping.MappingUtils;
import com.skypro.auction.model.Lot;
import com.skypro.auction.prejection.BidderNameAndBidDate;
import com.skypro.auction.repository.BidRepository;
import com.skypro.auction.repository.LotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LotService {

    private final LotRepository lotRepository;
    private final BidRepository bidRepository;

    public LotService(LotRepository lotRepository, BidRepository bidRepository) {
        this.lotRepository = lotRepository;
        this.bidRepository = bidRepository;
    }


    public LotDTO getLotById(Long lotId) {
        log.info("Getting lot with id: " + lotId);
        return MappingUtils.fromLotToLotDTO(lotRepository.findById(lotId).orElse(null));
    }

    public LotDTO createLot(CreatedLotDTO createdLotDTO){
        log.info("Create a new lot");
        Lot lot = MappingUtils.fromCreatedLotDTOToLot(createdLotDTO);
        lot.setStatus(LotStatus.CREATED);
        Lot createdLot = lotRepository.save(lot);
        log.info("New lot has been created");
        return MappingUtils.fromLotToLotDTO(createdLot);
    }

    public void updateLotStatusToStarted(Long lotId) {
        log.info("Updating lot status");
        Lot lot = lotRepository.findById(lotId).get();
        lot.setStatus(LotStatus.STARTED);
        lotRepository.save(lot);
        log.info("Lot status has been updated successfully. Now, it's " + LotStatus.STARTED);
    }

    public void updateLotStatusToStopped(Long lotId) {
        log.info("Updating lot status");
        Lot lot = lotRepository.findById(lotId).get();
        lot.setStatus(LotStatus.STOPPED);
        lotRepository.save(lot);
        log.info("Lot status has been updated successfully. Now, it's " + LotStatus.STOPPED);
    }

    public BidderNameAndBidDate getInfoAboutFirstBidder(Long id){
        log.info("Get information about first bidder on lot");
        return bidRepository.getInfoAboutFirstBidder(id);
    }

    public BidderNameAndBidDate getInfoAboutLudoman(Long id) {
        log.info("Get information about bidder who placed the most bids on the lot");
        return bidRepository.getInfoAboutLudoman(id);
    }

    public FullLotDTO getInfoAboutLot(Long id) {
        log.info("Get information about lot with id: " + id);
        FullLotDTO fullLotDTO = MappingUtils.fromLotDTOToFullLotDTO(getLotById(id));
        Integer currentPrice = bidRepository.bidCount(id) * fullLotDTO.getBidPrice() + fullLotDTO.getStartPrice();
        fullLotDTO.setCurrentPrice(currentPrice);
        if(bidRepository.bidCount(id) != 0) {
            BidDTOforFullLot bidDTO = new BidDTOforFullLot();
            bidDTO.setBidderName(bidRepository.getInfoAboutLastBidDate(id).getBidder_name());
            bidDTO.setBidDate(bidRepository.getInfoAboutLastBidDate(id).getBid_date());
            fullLotDTO.setLastBid(bidDTO);
            return fullLotDTO;
        }
        return fullLotDTO;
    }

    public Collection<LotDTO> getAllLots(LotStatus lotStatus, Integer pageNumber) {
        log.info("Get all lots");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 10);
        return lotRepository.findAllByStatus(lotStatus).stream()
                .map(MappingUtils::fromLotToLotDTO)
                .collect(Collectors.toList());
    }

    public Collection<FullLotDTO> getAllLotsForExport() {
        Collection<LotDTO> lots = lotRepository.findAll().stream()
                .map(MappingUtils::fromLotToLotDTO)
                .collect(Collectors.toList());
        Collection<FullLotDTO> exportLots = lots.stream()
                .map(MappingUtils::fromLotDTOToFullLotDTO)
                .collect(Collectors.toList());
        return exportLots;
    }






    public boolean checkToCorrect (CreatedLotDTO createdLotDTO) {
        log.info("Checking lot fields to correct");
        if(createdLotDTO.getTitle() == null || createdLotDTO.getTitle().isEmpty()) {
            return false;
        } else if (createdLotDTO.getDescription() == null || createdLotDTO.getDescription().isEmpty()) {
            return false;
        } else if (createdLotDTO.getStartPrice() == null || createdLotDTO.getBidPrice() == null) {
            return false;
        } else {
            log.info("The check was successful");
            return true;
        }
    }


}
