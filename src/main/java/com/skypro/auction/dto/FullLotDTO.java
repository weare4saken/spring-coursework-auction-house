package com.skypro.auction.dto;

import com.skypro.auction.enums.LotStatus;
import com.skypro.auction.model.Bid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FullLotDTO {

    private Long id;
    private LotStatus status;
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;
    private List<Bid> bids;


}
