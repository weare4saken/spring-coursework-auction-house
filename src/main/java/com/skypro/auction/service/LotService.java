package com.skypro.auction.service;

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



}
