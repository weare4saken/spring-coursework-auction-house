package com.skypro.auction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatedLotDTO {

    private Long id;
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;


}
