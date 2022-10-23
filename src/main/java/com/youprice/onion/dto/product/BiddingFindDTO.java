package com.youprice.onion.dto.product;

import com.youprice.onion.entity.product.Bidding;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BiddingFindDTO {

    private Long biddingId;
    private Long productId; //Product FK
    private Long memberId; //Member FK
    private int bid; //입찰가
    private LocalDateTime biddingTime; //입찰시간

    public BiddingFindDTO (Bidding bidding) {

        biddingId = bidding.getId();
        productId = bidding.getProduct().getId();
        memberId = bidding.getMember().getId();
        bid = bidding.getBid();
        biddingTime = bidding.getBiddingTime();
    }
}
