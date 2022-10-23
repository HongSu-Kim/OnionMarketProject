package com.youprice.onion.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BiddingAddDTO {

    private Long memberId; //회원번호

    private Long productId; //상품번호

    private int bid; //입찰가

}
