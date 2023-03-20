package com.skypro.auction.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.skypro.auction.enums.LotStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FullLotDTO {

    private Long id;
    private LotStatus status;
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;
    private Integer currentPrice;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BidDTOforFullLot lastBid;


}
