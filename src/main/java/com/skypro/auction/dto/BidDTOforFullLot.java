package com.skypro.auction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class BidDTOforFullLot {

    private String bidderName;
    private LocalDateTime bidDate;


}
