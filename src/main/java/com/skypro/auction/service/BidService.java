package com.skypro.auction.service;

import com.skypro.auction.dto.BidDTO;
import com.skypro.auction.dto.CreatedLotDTO;
import com.skypro.auction.dto.LotDTO;
import com.skypro.auction.enums.LotStatus;
import com.skypro.auction.mapping.MappingUtils;
import com.skypro.auction.model.Bid;
import com.skypro.auction.model.Lot;
import com.skypro.auction.repository.BidRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BidService {

    private final BidRepository bidRepository;

    public BidService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public BidDTO createBid(BidDTO bidDTO) {
        log.info("Create a new bid");
        Bid bid = MappingUtils.fromBidDTOtoBid(bidDTO);
        Bid createdBid = bidRepository.save(bid);
        log.info("New bid has been created");
        return MappingUtils.fromBidToBidDTO(createdBid);
    }

}
