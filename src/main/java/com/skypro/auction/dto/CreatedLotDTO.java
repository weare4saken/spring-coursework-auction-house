package com.skypro.auction.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatedLotDTO {

    @JsonIgnore
    private Long id;
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;


}
