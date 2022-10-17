package com.youprice.onion.dto.product;

import com.youprice.onion.entity.product.Auction;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AuctionFindDTO {

    private Long auctionId; //경매번호 PK
    private LocalDateTime auctionDeadLine; //경매 기한
    private String auctionStatus; //경매 여부

    public AuctionFindDTO(Auction auction) {

        auctionId = auction.getId();
        auctionDeadLine = auction.getAuctionDeadLine();
        auctionStatus = auction.getAuctionStatus();
    }
}
