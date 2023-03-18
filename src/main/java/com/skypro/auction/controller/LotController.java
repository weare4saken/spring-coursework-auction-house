package com.skypro.auction.controller;

import com.skypro.auction.dto.BidDTO;
import com.skypro.auction.dto.CreatedLotDTO;
import com.skypro.auction.dto.FullLotDTO;
import com.skypro.auction.dto.LotDTO;
import com.skypro.auction.enums.LotStatus;
import com.skypro.auction.model.Bid;
import com.skypro.auction.model.Lot;
import com.skypro.auction.prejection.BidderNameAndBidDate;
import com.skypro.auction.service.BidService;
import com.skypro.auction.service.LotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/lot")
public class LotController {

    private final LotService lotService;
    private final BidService bidService;

    public LotController(LotService lotService, BidService bidService) {
        this.lotService = lotService;
        this.bidService = bidService;
    }


    @PostMapping
    public ResponseEntity<LotDTO> createLot(@RequestBody CreatedLotDTO createdLotDTO) {
        if (!lotService.checkToCorrect(createdLotDTO)) {
            return ResponseEntity.badRequest().build();
        }
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
        bidDTO.setLotId(id);
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

    @GetMapping("/{id}/first")
    public ResponseEntity<BidderNameAndBidDate> getInfoAboutFirstBidder(@PathVariable Long id) {
        if(lotService.getLotById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        if(lotService.getLotById(id).getStatus().equals(LotStatus.CREATED)) {
            return ResponseEntity.badRequest().build();
        }
        BidderNameAndBidDate firstBidder = lotService.getInfoAboutFirstBidder(id);
        return ResponseEntity.ok(firstBidder);
    }

    @GetMapping("/{id}/frequent")
    public ResponseEntity<BidderNameAndBidDate> getInfoAboutLudoman(@PathVariable Long id) {
        if(lotService.getLotById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        if(lotService.getLotById(id).getStatus().equals(LotStatus.CREATED)) {
            return ResponseEntity.badRequest().build();
        }
        BidderNameAndBidDate ludoman = lotService.getInfoAboutLudoman(id);
        return ResponseEntity.ok(ludoman);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullLotDTO> getInfoAboutFullLot(@PathVariable Long id) {
        if(lotService.getLotById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lotService.getInfoAboutLot(id));
    }

    @GetMapping
    public ResponseEntity<Collection<LotDTO>> getAllLots(@RequestParam LotStatus lotStatus,
                                                         @RequestParam(name = "page", required = false) Integer pageNumber) {
        if(pageNumber == null){
            pageNumber = 1;
        }
        return ResponseEntity.ok(lotService.getAllLots(lotStatus, pageNumber));
    }

}

