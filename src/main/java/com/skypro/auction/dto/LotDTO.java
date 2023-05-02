package com.skypro.auction.dto;

import com.skypro.auction.enums.LotStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LotDTO {

    private Long id;
    private LotStatus status;
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;


}
