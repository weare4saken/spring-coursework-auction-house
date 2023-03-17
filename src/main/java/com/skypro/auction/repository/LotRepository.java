package com.skypro.auction.repository;

import com.skypro.auction.model.Bid;
import com.skypro.auction.model.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {

//    @Query(value = "SELECT * FROM lots JOIN bids ON lots.lot_id = bids.lot_id " +
//            "WHERE id=? ORDER BY bid_date ASC LIMIT 1", nativeQuery = true)
//    Bid getInfoAboutFirstBidder();

}
