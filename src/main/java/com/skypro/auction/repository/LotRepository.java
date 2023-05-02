package com.skypro.auction.repository;

import com.skypro.auction.enums.LotStatus;
import com.skypro.auction.model.Lot;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {

    Collection<Lot> findAllByStatus(LotStatus lotStatus, PageRequest pageRequest);


}


