package com.skypro.auction.repository;

import com.skypro.auction.enums.LotStatus;
import com.skypro.auction.model.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {

    public Collection<Lot> findAllByStatus(LotStatus lotStatus);


}


