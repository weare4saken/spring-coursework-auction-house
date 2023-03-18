package com.skypro.auction.repository;

import com.skypro.auction.model.Lot;
import com.skypro.auction.prejection.BidderNameAndBidDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {

    @Query(value = "SELECT bidder_name, bid_date FROM bid b WHERE b.lot_id = ?1 ORDER BY bid_date LIMIT 1", nativeQuery = true)
    BidderNameAndBidDate getInfoAboutFirstBidder(Long id);

    @Query(value = "SELECT bidder_name, MAX(b.bids) AS max_bids, MAX(last_bid_date) AS bid_date " +
            "FROM (SELECT bidder_name, COUNT(*) AS bids, MAX(bid_date) AS last_bid_date " +
            "FROM bid WHERE lot_id = ?1 GROUP BY bidder_name) AS b " +
            "GROUP BY bidder_name ORDER BY max_bids DESC LIMIT 1", nativeQuery = true)
    BidderNameAndBidDate getInfoAboutLudoman(Long id);
}


