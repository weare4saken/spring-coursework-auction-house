package com.skypro.auction.controller;

import com.skypro.auction.dto.BidDTO;
import com.skypro.auction.dto.CreatedLotDTO;
import com.skypro.auction.dto.LotDTO;
import com.skypro.auction.enums.LotStatus;
import com.skypro.auction.model.Bid;
import com.skypro.auction.model.Lot;
import com.skypro.auction.service.BidService;
import com.skypro.auction.service.LotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lot")
public class LotController {

    private final LotService lotService;
    private final BidService bidService;

    public LotController(LotService lotService, BidService bidService) {
        this.lotService = lotService;
        this.bidService = bidService;
    }


    //если есть ошибки в полях вернуть 400 (СДЕЛАТЬ ПОТОМ)
    @PostMapping
    public ResponseEntity<LotDTO> createLot(@RequestBody CreatedLotDTO createdLotDTO) {
        LotDTO createdLot = lotService.createLot(createdLotDTO);
        return ResponseEntity.ok(createdLot);
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<Lot> startBidding(@PathVariable Long id) {
        LotDTO updatedLot = lotService.getLotById(id);
        if (updatedLot == null) {
            return ResponseEntity.notFound().build();
        }
        if (updatedLot.getStatus().equals(LotStatus.STARTED)) {
            return ResponseEntity.ok().build();
        }
        if (updatedLot.getStatus().equals(LotStatus.STOPPED)) {
            return ResponseEntity.badRequest().build();
        }
        if (updatedLot.getStatus().equals(LotStatus.CREATED)) {
            lotService.updateLotStatusToStarted(id);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/bid")
    public ResponseEntity<Bid> placeBet(@PathVariable Long id,
                                        @RequestBody BidDTO bidDTO) {
        LotDTO biddingLot = lotService.getLotById(id);
        if (biddingLot == null) {
            return ResponseEntity.notFound().build();
        }
        if (biddingLot.getStatus().equals(LotStatus.CREATED)
                || biddingLot.getStatus().equals(LotStatus.STOPPED)) {
            return ResponseEntity.badRequest().build();
        }
        bidService.createBid(bidDTO);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/{id}/stop")
    public ResponseEntity<Lot> stopBidding(@PathVariable Long id) {
        LotDTO updatedLot = lotService.getLotById(id);
        if (updatedLot == null) {
            return ResponseEntity.notFound().build();
        }
        if (updatedLot.getStatus().equals(LotStatus.STOPPED)) {
            return ResponseEntity.ok().build();
        }
        if (updatedLot.getStatus().equals(LotStatus.CREATED)) {
            return ResponseEntity.badRequest().build();
        }
        if (updatedLot.getStatus().equals(LotStatus.STARTED)) {
            lotService.updateLotStatusToStopped(id);
        }
        return ResponseEntity.ok().build();
    }


}
