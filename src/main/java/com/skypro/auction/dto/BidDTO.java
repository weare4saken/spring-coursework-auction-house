package com.skypro.auction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class BidDTO {

    private Long id;
    private String bidderName;
    private LocalDateTime bidDate;

    public LocalDateTime getBidDate() {
        return LocalDateTime.parse(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("hh:mm:ss dd.MM.yyyy")));
    }
}
