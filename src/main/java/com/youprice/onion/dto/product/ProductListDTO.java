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
public class ProductListDTO {

    private Long productId;
    private String subject; //제목
    private String content; //내용
    private int price; //상품가격
    private int bid; //입찰가격
    private LocalDateTime uploadDate; //등록시간
    private LocalDateTime updateDate; //수정일
    private LocalDateTime auctionDeadline; //경매기한
    private String productImageName; //상품 이미지
    private Boolean blindStatus; //블라인드현황
    private Boolean payStatus; //페이 현황
    private ProductProgress productProgress; //판매상태 SALESON,RESERVED,TRADINGS,SOLDOUT 판매중,예약중,거래중,판매완료
    private Long categoryId; //카테고리 아이디
    private Boolean wishCheck; //찜 체크 여부

    public ProductListDTO(Product product) {

        this.productId = product.getId();
        this.subject = product.getSubject();
        this.content = product.getContent();
        this.price = product.getPrice();
        this.bid = product.getBiddingList().lastIndexOf(getBid());
        if(uploadDate==null) {
            this.uploadDate = product.getUploadDate();
        }
        if(uploadDate!=null) {
            this.updateDate = product.getUpdateDate();
        }
        this.auctionDeadline = product.getAuctionDeadline();
        this.productImageName = product.getRepresentativeImage();
        this.blindStatus = product.getBlindStatus();
        this.payStatus = product.getPayStatus();
        this.productProgress = product.getProductProgress();
        this.categoryId = product.getCategory().getId();
    }


}
