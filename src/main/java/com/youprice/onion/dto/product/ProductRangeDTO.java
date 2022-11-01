package com.youprice.onion.dto.product;

import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductProgress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductRangeDTO {

    private Long productId;
    private Long memberId; //Member FK
    private Long townId; //Town FK
    private Long categoryId; //Category FK
    private Long auctionId; //Auction FK
    private String subject; //제목
    private String content; //내용
    private int price; //상품가격
    private String representativeImage; //대표이미지
    private LocalDateTime uploadDate; //등록시간
    private LocalDateTime updateDate; //수정일
    private LocalDateTime auctionDeadline; //경매기한
    private int viewCount; //조회수
    private ProductProgress productProgress; //판매상태 SALESON,RESERVED,TRADINGS,SOLDOUT 판매중,예약중,거래중,판매완료
    private Boolean payStatus; //페이현황
    private Boolean blindStatus; //블라인드현황

    private Double latitude; //위도

    private Double longitude; //경도

    private Double wishDistance;

    public ProductRangeDTO(Product product) {

        productId = product.getId();
        memberId = product.getMember().getId();
        townId = product.getTown().getId();
        categoryId = product.getCategory().getId();
        subject = product.getSubject();
        content = product.getContent();
        price = product.getPrice();
        representativeImage = product.getRepresentativeImage();
        uploadDate = product.getUploadDate();
        updateDate = product.getUpdateDate();
        auctionDeadline = product.getAuctionDeadline();
        viewCount = product.getViewCount();
        productProgress = product.getProductProgress();
        payStatus = product.getPayStatus();
        blindStatus = product.getBlindStatus();
        latitude = product.getTown().getCoordinate().getLatitude();
        longitude = product.getTown().getCoordinate().getLongitude();
        wishDistance = product.getTown().getWishDistance();

    }



}
