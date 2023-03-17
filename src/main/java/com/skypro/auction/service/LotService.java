package com.skypro.auction.service;

import com.skypro.auction.dto.CreatedLotDTO;
import com.skypro.auction.dto.LotDTO;
import com.skypro.auction.enums.LotStatus;
import com.skypro.auction.mapping.MappingUtils;
import com.skypro.auction.model.Lot;
import com.skypro.auction.repository.BidRepository;
import com.skypro.auction.repository.LotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        return MappingUtils.fromLotToLotDTO(lotRepository.findById(lotId).get());
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





}