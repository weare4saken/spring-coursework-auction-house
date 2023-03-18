package com.skypro.auction.prejection;

import java.time.LocalDateTime;
//ЧТО-ТО ПРИДУМАТЬ С snake_case!
public interface BidderNameAndBidDate {
    String getBidder_name();
    LocalDateTime getBid_date();

}
