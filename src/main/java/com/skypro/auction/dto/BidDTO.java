package com.skypro.auction.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class BidDTO {

    @JsonIgnore
    private Long id;
    private String bidderName;
    @JsonIgnore
    private LocalDateTime bidDate;
    @JsonIgnore
    private Long lotId;

   /* public LocalDateTime getBidDate() {
        return LocalDateTime.parse(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
    }*/
}
