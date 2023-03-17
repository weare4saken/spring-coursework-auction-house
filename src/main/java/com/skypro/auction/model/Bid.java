package com.skypro.auction.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bidderName;
    private LocalDateTime bidDate;


//    public LocalDateTime getBidDate() {
//        return LocalDateTime.parse(LocalDateTime.now()
//                .format(DateTimeFormatter.ofPattern("hh:mm:ss dd.MM.yyyy")));
//    }

}
