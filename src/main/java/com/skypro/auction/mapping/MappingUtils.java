package com.skypro.auction.mapping;

import com.skypro.auction.dto.BidDTO;
import com.skypro.auction.dto.CreatedLotDTO;
import com.skypro.auction.dto.LotDTO;
import com.skypro.auction.model.Bid;
import com.skypro.auction.model.Lot;

public class MappingUtils {

    public static Lot fromCreatedLotDTOToLot(CreatedLotDTO createdLotDTO){
        Lot lot = new Lot();
        lot.setId(createdLotDTO.getId());
        lot.setTitle(createdLotDTO.getTitle());
        lot.setDescription(createdLotDTO.getDescription());
        lot.setStartPrice(createdLotDTO.getStartPrice());
        lot.setBidPrice(createdLotDTO.getBidPrice());
        return lot;
    }

    public static LotDTO fromLotToLotDTO(Lot lot) {
        LotDTO dto = new LotDTO();
        dto.setId(lot.getId());
        dto.setStatus(lot.getStatus());
        dto.setTitle(lot.getTitle());
        dto.setDescription(lot.getDescription());
        dto.setStartPrice(lot.getStartPrice());
        dto.setBidPrice(lot.getBidPrice());
        return dto;
    }

    /*public static Lot fromLotDTOToLot(LotDTO lotDTO) {
        Lot lot = new Lot();
        lot.setId(lotDTO.getId());
        lot.setStatus(lotDTO.getStatus());
        lot.setTitle(lotDTO.getTitle());
        lot.setDescription(lotDTO.getDescription());
        lot.setStartPrice(lotDTO.getStartPrice());
        lot.setBidPrice(lotDTO.getBidPrice());
        return lot;
    }*/

    public static BidDTO fromBidToBidDTO(Bid bid) {
        BidDTO dto = new BidDTO();
        dto.setId(bid.getId());
        dto.setBidderName(bid.getBidderName());
        dto.setBidDate(bid.getBidDate());
    }

    public static Bid fromBidDTOtoBid(BidDTO bidDTO) {
        Bid bid = new Bid();
        bid.setId(bidDTO.getId());
        bid.setBidderName(bidDTO.getBidderName());
        bid.setBidDate(bidDTO.getBidDate());
        return bid;
    }

}
