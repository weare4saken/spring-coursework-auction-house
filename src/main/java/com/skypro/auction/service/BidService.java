package com.skypro.auction.service;

import com.skypro.auction.dto.BidDTO;
import com.skypro.auction.mapping.MappingUtils;
import com.skypro.auction.model.Bid;
import com.skypro.auction.model.Lot;
import com.skypro.auction.repository.BidRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class BidService {

    private final BidRepository bidRepository;
    private final LotService lotService;

    public BidService(BidRepository bidRepository, LotService lotService) {
        this.bidRepository = bidRepository;
        this.lotService = lotService;
    }

    public BidDTO createBid(BidDTO bidDTO) {
        log.info("Create a new bid");
        Lot lot = MappingUtils.fromLotDTOToLot(lotService.getLotById(bidDTO.getLotId()));
        Bid bid = MappingUtils.fromBidDTOtoBid(bidDTO);
        bid.setLot(lot);
        bid.setBidDate(LocalDateTime.now());
        Bid createdBid = bidRepository.save(bid);
        log.info("New bid has been created");
        return MappingUtils.fromBidToBidDTO(createdBid);
    }

}
