package com.skypro.auction.repository;

import com.skypro.auction.model.Bid;
import com.skypro.auction.model.Lot;
import com.skypro.auction.prejection.FirstBidder;
import liquibase.repackaged.net.sf.jsqlparser.statement.select.First;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {

    @Query(value = "SELECT bidder_name, bid_date FROM bid b WHERE b.lot_id = ?1 ORDER BY bid_date ASC LIMIT 1", nativeQuery = true)
    FirstBidder getInfoAboutFirstBidder(Long id);

}
