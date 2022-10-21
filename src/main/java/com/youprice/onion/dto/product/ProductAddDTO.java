package com.youprice.onion.dto.product;

import lombok.*;

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

    private String content; //설명

    private int price; //상품가격

    private LocalDateTime auctionDeadline; //경매기한
    private Boolean auctionStatus; //경매현황
    private Boolean payStatus; //페이현황

    private String productImageName; //이미지 이름
    private String representativeImage; //대표이미지
}
