package com.skypro.auction.model;

import com.skypro.auction.enums.LotStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private LotStatus status;
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;
    @OneToMany(mappedBy = "lot")
    private List<Bid> bids;


}
