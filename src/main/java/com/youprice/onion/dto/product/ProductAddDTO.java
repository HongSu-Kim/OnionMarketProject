package com.youprice.onion.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductAddDTO {

    private Long memberId; //회원번호
    private Long townId; //동네번호
    private Long categoryId; //카테고리번호
    private Long orderId; //주문번호

    private String subject; //제목
    private String content; //내용
    private int price; //상품가격
    private String payStatus; //페이현황
    private LocalDateTime auctionDeadline; //경매기한
    private Boolean auctionStatus; //경매현황

    private String productImageName;//이미지 이름
}
