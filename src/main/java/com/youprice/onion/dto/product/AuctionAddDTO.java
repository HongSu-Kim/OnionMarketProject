package com.youprice.onion.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AuctionAddDTO {

    private LocalDateTime auctionDeadLine; //경매 마감 일시

    private String auctionStatus; //경매현황

}
